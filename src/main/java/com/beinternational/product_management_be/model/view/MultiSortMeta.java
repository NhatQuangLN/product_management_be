package com.beinternational.product_management_be.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiSortMeta {
	private String field;

	private Integer order;
}
