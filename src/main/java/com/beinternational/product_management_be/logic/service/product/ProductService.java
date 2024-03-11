package com.beinternational.product_management_be.logic.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.beinternational.product_management_be.logic.repository.product.IProductRepository;
import com.beinternational.product_management_be.model.db.product.Product;
import com.beinternational.product_management_be.model.view.MultiSortMeta;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Page<Product> findAll(Integer page, Integer size, List<MultiSortMeta> sortList) {

		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null) {
			size = 10;
		}
		page = page - 1;

		// start sort
		if (sortList == null) {
			sortList = new ArrayList<>();
		}
		List<Sort.Order> orders = new ArrayList<>();
		for (MultiSortMeta sortMeta : sortList) {
			Sort.Direction direction = (sortMeta.getOrder() == 1) ? Sort.Direction.ASC : Sort.Direction.DESC;
			if (sortMeta.getField().equals("productCategoryName")) {
				orders.add(new Sort.Order(direction, "productCategory.categoryName"));
				continue;
			}
			orders.add(new Sort.Order(direction, sortMeta.getField()));
		}
		Sort sort = Sort.by(orders);

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return productRepository.findAll(pageRequest);
	}

	@Override
	public Product findById(String productCode) {
		if (productCode == null) {
			return null;
		}

		Optional<Product> productFind = productRepository.findById(productCode);

		if (!productFind.isPresent()) {
			return null;
		}

		return productFind.get();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteById(String productCode) {
		productRepository.deleteById(productCode);
	}
}
