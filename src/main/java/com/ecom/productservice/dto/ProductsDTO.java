package com.ecom.productservice.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsDTO {

	private UUID id;
	private UUID productItemId;
	private String brand;
	private String description;
	private BigDecimal price;
	private boolean available;
	private OffsetDateTime createdOn;
	private OffsetDateTime updatedOn;
	private String createdBy;
	private String updatedBy;

}
