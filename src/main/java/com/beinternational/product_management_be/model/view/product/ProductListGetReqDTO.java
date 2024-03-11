package com.beinternational.product_management_be.model.view.product;

import java.util.List;

import com.beinternational.product_management_be.model.view.MultiSortMeta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListGetReqDTO {

	// current page
	private Integer p;

	// size (records per page)
	private Integer s;

	private String productName;

	private String productCategoryId;

	private String productBrand;

	private String productType;

	private String productDescription;

	private List<MultiSortMeta> sortList;
}
