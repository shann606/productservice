package com.ecom.productservice.entity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "online_variants")
public class Variants {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "category_id", nullable = false, insertable = false, updatable = false)
	private UUID categoryId;

	@Column(name = "variant_name")
	private String variantName;

	@Column(name = "available", nullable = false)
	private boolean available;

	@Column(name = "created_on", columnDefinition = "Date")
	private OffsetDateTime createdOn;

	@Column(name = "updated_on", columnDefinition = "Date")
	private OffsetDateTime updatedOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

	@JoinColumn(name = "category_var_id")
	private List<ProductItems> productItems;

	@PrePersist
	public void generateId() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}

}
