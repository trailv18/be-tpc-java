package com.lvtrai.betpc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lvtrai.betpc.model.Category;

public interface CategoryService {
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean save(Category category);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Optional<Category> findById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

    Category findByName(String name);
}