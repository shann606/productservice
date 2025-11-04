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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "online_category") // Avoid using reserved names like "user"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "available", nullable = false)
	private boolean available; // Will map to NUMBER(1) in Oracle

	@Column(name = "created_on", columnDefinition = "Date")
	private OffsetDateTime createdOn;

	@Column(name = "updated_on", columnDefinition = "Date")
	private OffsetDateTime updatedOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", nullable = false )
	private List<Variants> variants;
	
	
	@PrePersist
	public void generateId() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}
	
	
	

	
}
