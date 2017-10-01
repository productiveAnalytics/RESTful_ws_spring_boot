package com.productiveanalytics.restful_ws_spring_boot.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.productiveanalytics.restful_ws_spring_boot.model.Order;

@Repository
public final class OrderRepository extends InMemoryRepository<Order> implements InitializingBean {
	
	private void test() {
//		Order initOrder = new Order(0L, "Test order", 1L, false);
//		this.create(initOrder);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.test();
	}
	
	@Override
    protected void updateIfExists(Order original, Order updated) {
        original.setDescription(updated.getDescription());
        original.setCostInCents(updated.getCostInCents());
        original.setComplete(updated.isComplete());
    }

}