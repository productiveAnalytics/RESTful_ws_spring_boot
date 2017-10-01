package com.productiveanalytics.restful_ws_spring_boot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.productiveanalytics.restful_ws_spring_boot.model.Order;

@Component
public class OrderResourceAssembler 
			 extends ResourceAssembler <Order, OrderResource>
{
    @Autowired
    protected EntityLinks entityLinks;
    
    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";
	
	@Override
	public OrderResource toResource(final Order order) {
		OrderResource resource = new OrderResource(order);
		
        final Link selfLink = entityLinks.linkToSingleResource(order);
        
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE_REL));
        resource.add(selfLink.withRel(DELETE_REL));
        
        return resource;
	}

}