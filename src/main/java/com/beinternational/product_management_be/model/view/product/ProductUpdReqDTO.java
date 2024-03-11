package com.beinternational.product_management_be.model.view.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdReqDTO {

	private String productName;

	private String productCategoryId;

	private String productBrand;

	private String productType;

	private String productDescription;
}
