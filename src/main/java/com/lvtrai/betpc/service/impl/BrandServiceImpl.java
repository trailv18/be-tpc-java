package com.lvtrai.betpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lvtrai.betpc.model.Brand;
import com.lvtrai.betpc.repository.BrandRepository;
import com.lvtrai.betpc.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public boolean save(Brand brand) {
		Brand cBrand = brandRepository.findByName(brand.getName());
		if(cBrand != null) {
			return false;
		}
		brandRepository.save(brand);
		return true;
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public Optional<Brand> findById(Long id) {
		return brandRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		brandRepository.deleteById(id);
	}

	@Override
	public Brand findByName(String name) {
		return brandRepository.findByName(name);
	}

}
