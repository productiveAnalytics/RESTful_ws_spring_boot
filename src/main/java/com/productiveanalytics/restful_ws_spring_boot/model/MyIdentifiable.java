package com.productiveanalytics.restful_ws_spring_boot.model;

import java.io.Serializable;

public interface MyIdentifiable extends org.springframework.hateoas.Identifiable<Long>, Serializable {
	public void setId(Long id);
}