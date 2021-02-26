package com.lvtrai.betpc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.lvtrai.betpc.model.Brand;

public interface BrandService {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	boolean save(Brand brand);

	List<Brand> findAll();

	Page<Brand> findAll(Pageable pageable);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Optional<Brand> findById(Long id);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteById(Long id);

	Brand findByName(String name);
}
