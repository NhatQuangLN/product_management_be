package com.beinternational.product_management_be.logic.service.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beinternational.product_management_be.logic.repository.category.ICategoryRepository;
import com.beinternational.product_management_be.model.db.category.Category;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(UUID categoryId) {

		Optional<Category> categoryFind = categoryRepository.findById(categoryId);

		if (!categoryFind.isPresent()) {
			return null;
		}

		return categoryFind.get();
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
}
