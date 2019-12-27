package com.wq.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.wq.domain.OrderItem;
import com.wq.domain.Product;

public interface IOrderItemService {
	
	public void addOrderItem(OrderItem orderItem) throws Exception;

	public List<OrderItem> findOrderItemByOrderId(String order_id) throws Exception;

	public void deleteOrderItemByOrderId(String order_id) throws Exception;
	
	public List<OrderItem> findOrderItemsByProductId(String product_id) throws Exception;
	
	public List<String> findAllDistinctProductId() throws Exception;
	
}
