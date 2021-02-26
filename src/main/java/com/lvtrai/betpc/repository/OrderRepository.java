package com.lvtrai.betpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.lvtrai.betpc.model.Order;
import com.lvtrai.betpc.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUser(User user); 
	
	@EntityGraph(attributePaths = { "cartItems", "payment", "shipping" })
	Order findEagerById(Long id);

}
