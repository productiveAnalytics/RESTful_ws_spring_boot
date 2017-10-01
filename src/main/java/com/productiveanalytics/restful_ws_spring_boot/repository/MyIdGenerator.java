package com.productiveanalytics.restful_ws_spring_boot.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ID Generator. Using Prototype scope as the IdGenerator can be used for Entity specific.
 *  
 * @author LChawathe
 */

@Component("idGenerator")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MyIdGenerator {
	private AtomicLong nextId = new AtomicLong(1);
	
	public long getNextId() {
		return nextId.getAndIncrement();
	}
}
