package com.productiveanalytics.restful_ws_spring_boot.controller;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.ExposesResourceFor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productiveanalytics.restful_ws_spring_boot.model.Order;
import com.productiveanalytics.restful_ws_spring_boot.repository.OrderRepository;
import com.productiveanalytics.restful_ws_spring_boot.resources.OrderResource;
import com.productiveanalytics.restful_ws_spring_boot.resources.OrderResourceAssembler;

/**
 * REST endpoint
 * POST, GET, PUT, DELETE = C.R.U.D.
 * 
 * @author LChawathe
 */

@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(path = "/order", produces = "application/json")
public class OrderController
{
	private final Log LOGGER = LogFactory.getLog(OrderController.class);
	
	@Autowired
	private OrderRepository orderRepository; 
	
	@Autowired
	private OrderResourceAssembler orderResourceAssembler;
	
	
	/**
	 * POST = create
	 * @param order
	 */
	@RequestMapping (method = RequestMethod.POST
					,consumes = "application/json")
	public ResponseEntity<OrderResource> createOrder(@RequestBody Order order)
	{
		LOGGER.debug("[POST] received new Order: "+ order.toString());
		Order newOrder = orderRepository.create(order);
		LOGGER.debug("[POST] Created new Order: "+ order.getId());
		OrderResource orderRes = orderResourceAssembler.toResource(newOrder);
		return new ResponseEntity<OrderResource>(orderRes, HttpStatus.CREATED); // 201 : Created
	}
	
	
	/**
	 * GET all
	 */
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<Collection<OrderResource>> findAllOrders()
	{
		LOGGER.debug("[GET *] received request for all Orders");
		Collection<Order> allOrders = orderRepository.findAll();
		LOGGER.debug("[GET *] Retrieving total "+ orderRepository.getCount() +" Orders");
		Collection<OrderResource> allOrderResources = orderResourceAssembler.toResourceCollection(allOrders);
		return new ResponseEntity<>(allOrderResources, HttpStatus.OK); // 200 : Ok
	}
	
	/**
	 * GET by Id
	 * @param id
	 */
	@RequestMapping (method = RequestMethod.GET
					,path = "/{id}")
	public ResponseEntity<OrderResource> findOrderById(@PathVariable Long id)
	{
		LOGGER.debug("[GET Id] received request for Order: "+ id);
		Optional<Order> optionalOrder = orderRepository.findById(id);
		
		if (! optionalOrder.isPresent()) {
			LOGGER.error("[GET Id] Not Found Order: "+ id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 : Not found
		} else {
			Order foundOrder = optionalOrder.get();
			LOGGER.debug("[GET Id] Found Order: "+ foundOrder.toString());
			OrderResource foundOrderRes = orderResourceAssembler.toResource(foundOrder);
			return new ResponseEntity<OrderResource>(foundOrderRes, HttpStatus.OK); // 200 : Ok
		}
	}
	
	
	/**
	 * PUT = update
	 * @param id
	 * @param updatedOrder
	 */
	@RequestMapping (method = RequestMethod.PUT
					,path = "/{id}"
					,consumes = "application/json")
	public ResponseEntity<OrderResource> updateOrder(@PathVariable Long id, @RequestBody Order updatingOrder)
	{
		LOGGER.debug("[PUT] received updated Order: "+ updatingOrder.toString());
		boolean updateSuccessful = orderRepository.update(id, updatingOrder);
		
		if (updateSuccessful) {
			LOGGER.debug("[PUT] Updated Order: "+ id);
			return this.findOrderById(id);
		} else {
			LOGGER.error("[PUT] Error Updated Order: "+ id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 : Not found
		}
	}
	
	
	/**
	 * DELETE = delete
	 * @param id
	 */
	@RequestMapping (method = RequestMethod.DELETE
					,path = "/{id}")
	public ResponseEntity<OrderResource> deleteOrder(@PathVariable Long id)
	{
		boolean deleteSuccessful = orderRepository.delete(id);
		
		HttpStatus responseStatus;
		if (deleteSuccessful) {
			LOGGER.debug("[DELETE] Deleted Order: "+ id);
			responseStatus = HttpStatus.NO_CONTENT; // 204
		} else {
			LOGGER.error("[DELETE] Error Deleting. Unable to locate Order: "+ id);
			responseStatus = HttpStatus.NOT_FOUND; // 404
		}
		return new ResponseEntity<>(responseStatus);
	}
}