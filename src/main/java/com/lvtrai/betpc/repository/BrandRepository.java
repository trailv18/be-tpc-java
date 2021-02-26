package com.lvtrai.betpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvtrai.betpc.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

	Brand findByName(String name);

}
