/********************************************************************egg***m******a**************n************


 * 
 * File: CategoryDTO.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	private String categoryName;

	public CategoryDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
