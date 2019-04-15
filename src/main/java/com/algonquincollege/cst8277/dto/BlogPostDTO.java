/********************************************************************egg***m******a**************n************

 * 
 * File: BlogPostDTO.java
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

public class BlogPostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	private String title;
	private String content;
	private String authorName;

	private CategoryDTO category;

	/**
	 * super constructor
	 */
	public BlogPostDTO() {
		super();
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return authorname
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return category
	 */
	public CategoryDTO getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

}
