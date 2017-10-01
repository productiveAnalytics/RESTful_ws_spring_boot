package com.productiveanalytics.restful_ws_spring_boot.resources;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.productiveanalytics.restful_ws_spring_boot.model.Order;

/**
 * OrderResource class has a one-to-one field relationship with the Order.
 * The primary reason to create a separate resource class is : 
 *    the resource class allows us to implement a level of indirection between the Order class itself 
 *    and how that class is presented.
 * 
 * @author LChawathe
 *
 */
public class OrderResource extends ResourceSupport {
	
	private final Long 		id;
	
	private final String 	description;
    private final long 		costInCents;
    private final boolean 	isComplete;
    
    /*
     * JsonUnwrapped allows to not pull Order in same level.
     */
//    @JsonUnwrapped
//    private final Order order;
//    
//    public OrderResource(Order order) {
//    	this.order = order;
//    }

    public OrderResource(Order order) {
        id = order.getId();
        description = order.getDescription();
        costInCents = order.getCostInCents();
        isComplete = order.isComplete();
    }
    
    @JsonProperty("id")
    public Long getResourceId() {
    	return this.id;
    }
    
    public String getDescription() {
		return this.description;
	}

    @JsonProperty("cost-in-cents")
	public long getCostInCents() {
		return this.costInCents;
	}

	public boolean isComplete() {
		return this.isComplete;
	}
}
