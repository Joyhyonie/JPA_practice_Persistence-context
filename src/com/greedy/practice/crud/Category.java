package com.greedy.practice.crud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="practice_category")
@Table(name="TBL_CATEGORY")
@SequenceGenerator(name="SEQ_CATEGORY_CODE_GENERATOR", sequenceName="SEQ_CATEGORY_CODE", initialValue=100, allocationSize=1)
public class Category {

	@Id
	@Column(name="CATEGORY_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CATEGORY_CODE_GENERATOR")
	private int categoryCode;
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	@Column(name="REF_CATEGORY_CODE")
	private int refCategoryCode;
	
	public Category() {}

	public Category(int categoryCode, String categoryName, int refCategoryCode) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.refCategoryCode = refCategoryCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getRefCategoryCode() {
		return refCategoryCode;
	}

	public void setRefCategoryCode(int refCategoryCode) {
		this.refCategoryCode = refCategoryCode;
	}

	@Override
	public String toString() {
		return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", refCategoryCode="
				+ refCategoryCode + "]";
	}

}
