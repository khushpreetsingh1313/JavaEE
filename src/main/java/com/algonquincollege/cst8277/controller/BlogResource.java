/********************************************************************egg***m******a**************n************

 * 
 * File: BlogResource.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.dao.SimpleBean;
import com.algonquincollege.cst8277.dto.BlogPostDTO;
import com.algonquincollege.cst8277.dto.CommentDTO;
import com.algonquincollege.cst8277.models.BlogPost;
import com.algonquincollege.cst8277.models.Category;
import com.algonquincollege.cst8277.models.Comment;

@Path("/blog")
public class BlogResource {

	public static final String ROLE_ADMIN = "ADMINS";
	public static final String ROLE_USER = "USERS";

	@EJB
	private SimpleBean simpleBean;

	/**
	 * @param category
	 * @return new category
	 */
	@POST
	@Path("/newcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ ROLE_ADMIN })
	public Response createNewCategory(Category category) {
		simpleBean.createNewCategory(category);
		return Response.ok().build();
	}

	/**
	 * @return list of all the categories
	 */
	@GET
	@Path("/categories")
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAllCategories() {
		List<Category> categories = simpleBean.getAllCategories();
		final GenericEntity<List<Category>> list = new GenericEntity<List<Category>>(categories) {
		};
		return Response.ok(list).build();
	}

	/**
	 * @param blogPost
	 * @return new post
	 */
	@POST
	@Path("/newpost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ ROLE_ADMIN })
	public Response createNewPost(BlogPost blogPost) {
		simpleBean.createNewPost(blogPost);
		return Response.ok().build();
	}

	/**
	 * @param bookId
	 * @param blogPost
	 * @return updated post
	 */
	@PUT
	@Path("/updatepost/{blogId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ ROLE_ADMIN })
	public Response updateBlogPost(@PathParam("blogId") int bookId, BlogPostDTO blogPost) {
		simpleBean.updateBlogPost(blogPost);
		return Response.ok().build();
	}

	/**
	 * @return list of all posts
	 */
	@GET
	@Path("/posts")
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAllBlogPosts() {
		List<BlogPost> posts = simpleBean.getAllPosts();
		final GenericEntity<List<BlogPost>> list = new GenericEntity<List<BlogPost>>(posts) {
		};
		return Response.ok(list).build();
	}

	/**
	 * @param id 
	 * @return delete post
	 */
	@DELETE
	@Path("/deletepost/{postId}")
	@RolesAllowed({ ROLE_ADMIN })
	public Response deleteBlogPost(@PathParam("postId") int id) {
		String deletePost = simpleBean.deletePost(id);
		return Response.ok().build();
	}

	/**
	 * @param category
	 * @return list of all posts by category
	 */
	@GET
	@Path("/postsbycategory/{category}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	public Response getPostsByCategory(@PathParam("category") String category) {
		List<BlogPost> posts = simpleBean.getAllPostsByCategory(category);
		final GenericEntity<List<BlogPost>> list = new GenericEntity<List<BlogPost>>(posts) {
		};
		return Response.ok(list).build();
	}

	/**
	 * @param author
	 * @return list of all posts by auther
	 */
	@GET
	@Path("/postsbyauthor/{author}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	public Response getPostsByAuthor(@PathParam("author") String author) {
		List<BlogPost> posts = simpleBean.getAllPostsByAuthor(author);
		final GenericEntity<List<BlogPost>> list = new GenericEntity<List<BlogPost>>(posts) {
		};
		return Response.ok(list).build();
	}

	/**
	 * @param postId
	 * @return list of all comments
	 */
	@GET
	@Path("/comments/{postId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	public Response getCommentsOnPost(@PathParam("postId") Integer postId) {
		List<Comment> comments = simpleBean.getAllCommentsOnPost(postId);
		final GenericEntity<List<Comment>> list = new GenericEntity<List<Comment>>(comments) {};
		return Response.ok(list).build();
	}

	/**
	 * @param comment
	 * @return add new comment
	 */
	@POST
	@Path("/addcomment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ ROLE_ADMIN, ROLE_USER })
	public Response addNewComment(CommentDTO comment) {
		simpleBean.addNewComment(comment);
		return Response.ok().build();
	}

	/**
	 * @param id
	 * @return delete comment
	 */
	@DELETE
	@Path("/deletecomment/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ ROLE_ADMIN })
	public Response deleteComment(@PathParam("commentId") int id) {
		simpleBean.deleteComment(id);
		return Response.ok().build();
	}

}
