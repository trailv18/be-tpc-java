package com.lvtrai.betpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lvtrai.betpc.model.Category;
import com.lvtrai.betpc.repository.CategoryRepository;
import com.lvtrai.betpc.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean save(Category category) {
        Category aCategory = this.findByName(category.getName());
        if (aCategory != null) {
            return false;
        }
        categoryRepository.save(category);
        return true;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
       return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

}