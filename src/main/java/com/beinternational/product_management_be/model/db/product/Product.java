package com.beinternational.product_management_be.model.db.product;

import java.time.LocalDateTime;

import com.beinternational.product_management_be.model.db.category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

	@Id
	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_name")
	private String productName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category productCategory;

	@Column(name = "product_brand")
	private String productBrand;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "created_at")
	public LocalDateTime createdAt;

	@Column(name = "updated_at")
	public LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
