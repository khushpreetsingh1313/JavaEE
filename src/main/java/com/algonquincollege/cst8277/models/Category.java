/********************************************************************egg***m******a**************n************

 * 
 * File: Category.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners({AuditListener.class})
public class Category extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String categoryName;
	
	/**
	 * super constructor
	 */
	public Category() {
		super();
	}
	
	/**
	 * @return categoryname
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
