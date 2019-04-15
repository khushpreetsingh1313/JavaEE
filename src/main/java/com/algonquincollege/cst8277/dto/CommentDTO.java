/********************************************************************egg***m******a**************n************

 * 
 * File: CommentDTO.java
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

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String content;
	private String commentby;
	private BlogPostDTO post;

	/**
	 *super constructor 
	 */
	public CommentDTO() {
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
	public BlogPostDTO getPost() {
		return post;
	}

	/**
	 * @param post
	 */
	public void setPost(BlogPostDTO post) {
		this.post = post;
	}

}
