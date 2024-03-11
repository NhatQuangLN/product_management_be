package com.beinternational.product_management_be.model.view.product;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailGetResDTO {

	private Product product = new Product();

	@lombok.Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Product {
		private String productCode;

		private String productName;

		private String productCategoryId;

		private String productBrand;

		private String productType;

		private String productDescription;

		private String createdAt;

		private String updatedAt;
	}

	private List<Category> categoryList = new ArrayList<>();

	@lombok.Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Category {
		private String categoryId;

		private String categoryName;

	}

}
