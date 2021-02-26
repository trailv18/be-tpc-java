package com.lvtrai.betpc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.data.domain.Pageable;

import com.lvtrai.betpc.model.Category;
import com.lvtrai.betpc.payload.response.MessageResponse;
import com.lvtrai.betpc.service.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {
    
	@Autowired
	private CategoryService categoryService;

//    @Autowired
   
    @GetMapping
    public ResponseEntity<Object> findAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Object> findAllCategories(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findCategoryById(@PathVariable Long id) {
    	Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Error: Can not find this category!"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
    	boolean success = categoryService.save(category);
        if (!success) {
            return new ResponseEntity<>(new MessageResponse("Error: Cannot save category!"), HttpStatus.BAD_REQUEST);
        }    	
        return new ResponseEntity<>(new MessageResponse("Save category successfully!"), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    	Optional<Category> categoryData = categoryService.findById(id);
	    if(categoryData.isPresent()) {
	    	Category updateCategory = categoryData.get();
	    	updateCategory.setName(category.getName());
	    	categoryService.save(updateCategory);
	        return new ResponseEntity<>(new MessageResponse("Update category successfully!"), HttpStatus.OK);
	    }else {
			return new ResponseEntity<>(new MessageResponse("Error: Cannot find this brand!"), HttpStatus.NOT_FOUND);
	    }
    }

//    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
//        Category category = categoryService.findById(id);
//        Iterable<Post> posts = postService.findByCategory_Id(id);
//
//        if (category == null) {
//            return new ResponseEntity<>(new ApiResponse(false, "Can not find this category!"), HttpStatus.NOT_FOUND);
//        }
//        if (posts.iterator().hasNext()) {
//            return new ResponseEntity<>(new ApiResponse(false, "Cannot delete this reason!"), HttpStatus.BAD_REQUEST);
//        }
//        categoryService.deleteById(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete repository successfully!"), HttpStatus.OK);
//    }

}
