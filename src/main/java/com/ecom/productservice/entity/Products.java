package com.ecom.productservice.entity;

import java.math.BigDecimal;
import java.sql.Clob;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "online_products")
public class Products {
	
	@Id
	@Column(name="id" , nullable = false)
	private UUID id;
	
	@Column(name="product_item_id" , nullable = false ,insertable = false , updatable = false)
	private UUID productItemId;
	
	@Column(name="product_brand" , nullable = false)
	private String brand;
	
	@Column(name="product_description" , nullable = false)
	private Clob description;
	
	@Column(name="product_price")
	private BigDecimal price;
	
	@Column(name="available" , nullable = false)
	private boolean available;
	
	@Column(name ="created_on" , columnDefinition = "Date")
	private OffsetDateTime createdOn;
	
	@Column(name ="updated_on" , columnDefinition = "Date")
	private OffsetDateTime updatedOn;
	
	@Column(name ="created_by")
	private String createdBy;
	
	@Column(name ="updated_by")
	private String updatedBy;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "product_item_id") private ProductItems productItem;
	 */
	
	
	@PrePersist
	public void generateId() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}

}
