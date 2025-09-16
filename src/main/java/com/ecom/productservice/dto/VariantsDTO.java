package com.ecom.productservice.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantsDTO {

	private UUID id;
	private UUID categoryId;
	private String variantName;
	private boolean available;
	private OffsetDateTime createdOn;
	private OffsetDateTime updatedOn;
	private String createdBy;
	private String updatedBy;
	private List<ProductItemsDTO> productItems;
	

}
