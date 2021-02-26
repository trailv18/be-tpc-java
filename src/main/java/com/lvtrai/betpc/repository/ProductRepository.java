package com.lvtrai.betpc.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lvtrai.betpc.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
		
	Page<Product> getProducts(Long productId, Pageable pageable);
	
	Optional<Product> findById(Long id);
		
	Iterable<Product> findByCategory_Id(Long categoryId);
	
	Iterable<Product> findByBrand_Id(Long brandId);

}
