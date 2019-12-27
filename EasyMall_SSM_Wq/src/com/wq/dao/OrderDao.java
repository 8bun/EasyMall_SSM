package com.wq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wq.domain.Order;

@Mapper
@Repository("orderDao")
public interface OrderDao {
	
	@Insert("insert into orders(id,money,receiverinfo,paystate,user_id) values(#{id},#{money},#{receiverinfo},#{paystate},#{user_id})")
	public void addOrder(Order order) throws Exception;
	
	@Select("select * from orders where user_id=#{user_id}")
	public List<Order> findOrdersByUserId(Integer user_id)throws Exception;
	
	@Delete("delete from orders where id=#{id}")
	public void deleteOrderById(String id)throws Exception;

	@Select("select * from orders")
	public List<Order> findAllOrders() throws Exception;

	@Select("select * from orders where id=#{id}")
	public Order findOrderByOrderId(String orderId) throws Exception;

	@Select("select * from orders where paystate=0")
	public List<Order> findAllOrderUnPay() throws Exception;
	
	@Select("select count(*) FROM orders WHERE DATEDIFF(ordertime,NOW())=0")
	public Integer findOrdersNumToday() throws Exception;
	
	@Select("select sum(money) FROM orders WHERE DATEDIFF(ordertime,NOW())=0")
	public Integer findOrdersMoneyToday() throws Exception;
	
	@Select("select sum(money) FROM orders WHERE TO_DAYS(NOW())-TO_DAYS(ordertime)<=1")
	public Integer findOrdersMoneyYestoday() throws Exception;
}