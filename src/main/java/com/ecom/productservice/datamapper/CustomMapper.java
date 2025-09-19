package com.ecom.productservice.datamapper;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecom.productservice.dto.CategoriesDTO;
import com.ecom.productservice.dto.CategoriesDTO.CategoryDTO;
import com.ecom.productservice.entity.Category;

@Mapper  (componentModel = "spring")
public interface CustomMapper {

	//@Mapping(source = "Products.description", target = "ProductsDTO.description", defaultValue = "descriptionToString")
	CategoriesDTO.CategoryDTO toCategoryDTO(Category enity);

	//@Mapping(source = "ProductsDTO.description", target = "Products.description", defaultValue = "descriptionToClob")
	Category toCategoryEntity(CategoriesDTO.CategoryDTO cat);
	
	//@Mapping(source = "Products.description", target = "ProductsDTO.description", defaultValue = "descriptionToString")
	List<CategoryDTO> toListCategoryDTO(List<Category> all);


	default String descriptionToString(Clob clob) {

		String desc = null;
		if (clob == null) {
			return null;
		}

		try {
			desc = clob.getSubString(1, (int) clob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desc;
	}

	default Clob descriptionToClob(String desc) {

		Clob clob = null;
		if (desc == null) {
			return null;
		}

		try {
			clob = new javax.sql.rowset.serial.SerialClob(desc.toCharArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clob;
	}


}
