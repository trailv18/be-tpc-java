package com.lvtrai.betpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.lvtrai.betpc.model.CartItem;
import com.lvtrai.betpc.model.User;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	@EntityGraph(attributePaths = { "product" })
	List<CartItem> findAllByUserAndOrderIsNull(User user);
	
	void deleteAllByUserAndOrderIsNull(User user);

	int countDistinctByUserAndOrderIsNull(User user);
}
