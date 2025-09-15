package com.ecom.productservice.dto;

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
public class Categories {
	
	UUID id;
	String categoryName;
	boolean online;
	OffsetDateTime createdOn;
	OffsetDateTime updatedOn;
	String createdBy;
	String updatedBy;

}
