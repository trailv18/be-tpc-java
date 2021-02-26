package com.lvtrai.betpc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvtrai.betpc.model.Brand;
import com.lvtrai.betpc.payload.response.MessageResponse;
import com.lvtrai.betpc.service.BrandService;

@RestController
@RequestMapping(path = "/api/v1/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping
	public ResponseEntity<Object> findAllBrands() {
		return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/pages")
	public ResponseEntity<Object> findAllBrands(@PageableDefault(size = 5) Pageable pageable) {
		return new ResponseEntity<>(brandService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findBrandById(@PathVariable Long id) {
		Optional<Brand> brand = brandService.findById(id);
		if (brand.isPresent()) {
			return new ResponseEntity<>(brand.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error: Cannot find brand!"), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> createBrand(@RequestBody Brand brand) {
		boolean success = brandService.save(brand);
		if (!success) {
			return new ResponseEntity<>(new MessageResponse("Error: Cannot save brand!"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new MessageResponse("Save brand successfully!"), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
		Optional<Brand> brandData = brandService.findById(id);
		if (brandData.isPresent()) {
			Brand updateBrand = brandData.get();
			updateBrand.setName(brand.getName());
			brandService.save(updateBrand);
			return new ResponseEntity<>(new MessageResponse("Update brand successfully!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error: Cannot find this brand!"), HttpStatus.NOT_FOUND);
		}
	}
}
