package com.beinternational.product_management_be.logic.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beinternational.product_management_be.model.db.product.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
	Page<Product> findAll(Pageable pageable);
}
