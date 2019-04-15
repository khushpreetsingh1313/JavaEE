/********************************************************************egg***m******a**************n************

 * 
 * File: BlogPost.java
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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners({AuditListener.class})
public class BlogPost extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String content;
	private String authorName;
	
	private Category category;

	/**
	 * super constructor
	 */
	public BlogPost() {
		super();
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
	@ManyToOne(cascade=CascadeType.ALL)
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
