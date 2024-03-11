package com.beinternational.product_management_be.model.view.product;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListGetResDTO {

	private List<Product> productList = new ArrayList<>();

	private Long totalRecords;

	@lombok.Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Product {
		private String productCode;

		private String productName;

		private String productCategoryName;

		private String productBrand;

		private String productType;

		private String productDescription;
	}
}
