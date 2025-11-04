package com.ecom.productservice.util;

import org.springframework.data.jpa.domain.Specification;

import com.ecom.productservice.entity.Category;

import jakarta.persistence.criteria.Predicate;

public class CategorySpecfication {

	private CategorySpecfication() {

	}

	public static Specification<Category> filterBy(String catName, boolean available, String createdBy) {

		return (root, query, cb) -> {

			Predicate pb = cb.conjunction();
			if (catName != null && !"".equals(catName)) {
				pb = cb.and(pb, cb.like(root.get("categoryName"), "%" + catName + "%"));
			}

			pb = cb.and(pb, cb.equal(root.get("available"), available));

			if (createdBy != null && !"".equals(createdBy)) {
				pb = cb.and(pb, cb.equal(root.get("createdBy"), createdBy));
			}

			return pb;

		};

	}

}
