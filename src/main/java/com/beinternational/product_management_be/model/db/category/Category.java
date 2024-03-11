package com.beinternational.product_management_be.model.db.category;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.beinternational.product_management_be.model.db.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "category_id")
	private UUID categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "created_at")
	public LocalDateTime createdAt;

	@Column(name = "updated_at")
	public LocalDateTime updatedAt;

	// MappedBy reference variable category in Product
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.ALL)
	// Do not use this field when using equal or hashCode
	@EqualsAndHashCode.Exclude
	public Set<Product> productList = new HashSet<>();

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
