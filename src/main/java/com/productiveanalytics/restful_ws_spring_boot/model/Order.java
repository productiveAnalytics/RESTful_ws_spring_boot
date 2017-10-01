package com.productiveanalytics.restful_ws_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Order implements MyIdentifiable {
	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = 3762747613223143004L;
	private static final ObjectMapper JSON_CONVERTER = new ObjectMapper();
	
	private Long 		id;
	
	private String 		description;
	
	@JsonProperty("cost-in-cents")
    private long 		costInCents;
	
    private boolean 	isComplete;
    
    public Order() { }
    
    public Order(Long id, String desc, long cost, boolean completeFlag) {
    	this.id = id;
    	
    	this.description = desc;
    	this.costInCents = cost;
    	this.isComplete  = completeFlag;
    }
    
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCostInCents() {
		return costInCents;
	}
	public void setCostInCents(long costInCents) {
		this.costInCents = costInCents;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	/*** Utility methods ***/
	public void markIncomplete() {
        setComplete(false);
    }
	public void markComplete() {
        setComplete(true);
    }
	
	/**
	 * Return JSON formatted Order
	 */
	public String toString() {
		try {
			return JSON_CONVERTER.writeValueAsString(this);
		} catch (JsonProcessingException jsonEx) {
			jsonEx.printStackTrace();
		}
		return super.toString();
	}
	
	public static void main (String[] args) {
		Order o1 = new Order(1L, "Web's first order", 100L, Boolean.TRUE.booleanValue());

		// Expected result : {"id":1,"description":"Web's first order","complete":true,"cost-in-cents":100}
		System.out.println(o1.toString());
	}
}
