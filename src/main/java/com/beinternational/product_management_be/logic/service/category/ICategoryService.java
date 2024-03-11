package com.beinternational.product_management_be.logic.service.category;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.beinternational.product_management_be.model.db.category.Category;

@Service
public interface ICategoryService {

	public List<Category> findAll();

	public Category findById(UUID categoryId);

	public Category save(Category category);
}
