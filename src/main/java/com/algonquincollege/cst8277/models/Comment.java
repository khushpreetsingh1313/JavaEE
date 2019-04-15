/********************************************************************egg***m******a**************n************

 * 
 * File: Comment.java
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
import javax.persistence.ManyToOne;

@Entity
@EntityListeners({AuditListener.class})
public class Comment extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String content;
	private String commentby;
	private BlogPost post;
	
	/**
	 * super constructor
	 */
	public Comment() {
		super();
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
	 * @return commentby
	 */
	public String getCommentby() {
		return commentby;
	}
	/**
	 * @param commentby
	 */
	public void setCommentby(String commentby) {
		this.commentby = commentby;
	}
	
	/**
	 * @return post
	 */
	@ManyToOne
	public BlogPost getPost() {
		return post;
	}
	/**
	 * @param post
	 */
	public void setPost(BlogPost post) {
		this.post = post;
	}

}
