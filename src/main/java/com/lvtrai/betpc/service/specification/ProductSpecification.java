package com.lvtrai.betpc.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.lvtrai.betpc.model.Product;

public class ProductSpecification {

	public static Specification<Product> hasCategoryId(Long categoryId) {
		return (root, query, criteriaBuilder) -> {
			if (categoryId == null)
				return criteriaBuilder.conjunction();
			return criteriaBuilder.equal(root.join("category").get("id"), categoryId);
		};
	}

	public static Specification<Product> hasBrandId(Long brandId) {
		return (root, query, criteriaBuilder) -> {
			if (brandId == null)
				return criteriaBuilder.conjunction();
			return criteriaBuilder.equal(root.join("brand").get("id"), brandId);
		};
	}

	public static Specification<Product> hasPriceLow(Integer priceLow) {
		return (root, query, criteriaBuilder) -> {
			if (priceLow != null && priceLow >= 0)
				return criteriaBuilder.conjunction();
			return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow);
		};
	}
	
	public static Specification<Product> hasPriceHigh(Integer priceHigh) {
		return (root, query, criteriaBuilder) -> {
			if (priceHigh != null && priceHigh >= 0)
				return criteriaBuilder.conjunction();
			return criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh);
		};
	}
	
	public static Specification<Product> search(String search) {
		return (root, query, criteriaBuilder) -> {
			if (search!=null && !search.isEmpty())
				return criteriaBuilder.conjunction();
			return criteriaBuilder.like(root.get("name"), "%"+search+"%");
		};
	}
}
