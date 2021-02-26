package com.lvtrai.betpc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.lvtrai.betpc.model.Product;

public interface ProductService {

	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, Long categoryId,
            Long brandId, String search);
		
	
	List<Product> findFirstProducts();

	
	Optional<Product> findProductById(Long id);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Product saveProduct(Product product);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteProductById(Long id);
	
	Iterable<Product> findByCategory_Id(Long categoryId);

    Iterable<Product> findByBrand_Id(Long brandId);
}
