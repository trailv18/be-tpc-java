package com.lvtrai.betpc.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lvtrai.betpc.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
}
