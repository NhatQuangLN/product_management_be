package com.beinternational.product_management_be.logic.repository.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beinternational.product_management_be.model.db.category.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {

}
