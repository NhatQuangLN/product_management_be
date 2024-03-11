package com.beinternational.product_management_be.model.view.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateReqDTO {
	private String productCode;

	private String productName;

	private String productCategoryId;

	private String productBrand;

	private String productType;

	private String productDescription;
}
