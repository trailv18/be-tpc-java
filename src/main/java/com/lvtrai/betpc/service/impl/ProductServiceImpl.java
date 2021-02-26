package com.lvtrai.betpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.lvtrai.betpc.model.Product;
import com.lvtrai.betpc.repository.ProductRepository;
import com.lvtrai.betpc.service.ProductService;
import com.lvtrai.betpc.service.specification.ProductSpecification;

public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Value("${productservice.featured-items-number}")
	private int featuredNumber;
	
	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, Long categoryId,
			Long brandId, String search) {
	       return productRepository.findAll(
	                Specification
	                        .where(ProductSpecification.hasCategoryId(categoryId))
	                        .and(ProductSpecification.hasBrandId(brandId))
	                        .and(ProductSpecification.hasPriceLow(priceLow))
	                        .and(ProductSpecification.hasPriceHigh(priceHigh))
	                , pageable);
	}

	@Override
	public List<Product> findFirstProducts() {
		return productRepository.findAll(PageRequest.of(0,featuredNumber)).getContent(); 
	}

	@Override
	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);		
	}

	@Override
	public Iterable<Product> findByCategory_Id(Long categoryId) {
		return productRepository.findByCategory_Id(categoryId);
	}

	@Override
	public Iterable<Product> findByBrand_Id(Long brandId) {
		return productRepository.findByBrand_Id(brandId);
	}

}
