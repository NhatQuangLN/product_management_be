package com.beinternational.product_management_be.logic.service.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beinternational.product_management_be.model.db.product.Product;
import com.beinternational.product_management_be.model.view.MultiSortMeta;

@Service
public interface IProductService {

	public Page<Product> findAll(Integer page, Integer size, List<MultiSortMeta> sortList);

	public Product findById(String productCode);

	public Product save(Product product);

	public void deleteById(String productCode);
}
