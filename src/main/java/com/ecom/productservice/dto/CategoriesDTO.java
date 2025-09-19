package com.ecom.productservice.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoriesDTO {

	private List<CategoriesDTO.CategoryDTO> categories;


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class CategoryDTO {

		private UUID id;
		private String categoryName;
		private boolean available;
		private OffsetDateTime createdOn;
		private OffsetDateTime updatedOn;
		private String createdBy;
		private String updatedBy;
		private List<VariantsDTO> variants;

	}
}
