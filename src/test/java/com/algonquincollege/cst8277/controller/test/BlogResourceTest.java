/********************************************************************egg***m******a**************n************

 * 
 * File: BlogResourceTest.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.algonquincollege.cst8277.dto.BlogPostDTO;
import com.algonquincollege.cst8277.dto.CategoryDTO;
import com.algonquincollege.cst8277.dto.CommentDTO;
import com.algonquincollege.cst8277.models.BlogPost;
import com.algonquincollege.cst8277.models.Category;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.JVM)
public class BlogResourceTest {

	private static final String TEST_PASSWORD_ENC = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
	private static final String TEST_USER_USER1 = "user1";
	private static final String TEST_USER_ADMIN1 = "admin";
	private static final String TEST_ROLE_USERS = "USERS";
	private static final String TEST_ROLE_ADMINS = "ADMINS";

	private static final String TEST_PASSWORD = "password";
	private static final String USERNAME_TEST_ADMIN = "admin";

	private static int DELETE_COMMENT_ID = 1;
	private static int DELETE_BLOG_ID = 1;
	private static int GET_COMMENTS_ONPOST_BLOGID = 1;

	private static final String ADD_NEW_COMMENT_CONTENT = "This article helped me a lot to understand the basics of Java.";
	private static final String ADD_NEW_COMMENT_CONTENTBY = "Max";

	private static final String GET_POSTS_BY_CATEGORY_CATNAME = "Programming";
	private static final String GET_POSTS_BY_AUTHOR_AUTHNAME = "Mehmood";

	private static int UPDATE_BLOGID = 1;
	private static final String UPDATE_BLOG_CONTENT = "Update:Java is an awesome programming lanaguage.";
	private static final String UPDATE_BLOG_AUTHOR = "Maximus";
	private static final String UPDATE_BLOG_TITLE = "Zero to Hero: Java 2";

	private static int CREATE_BLOG_CATEGORYID = 1;
	private static final String CREATE_BLOG_TITLE = "Zero to Hero: Java";
	private static final String CREATE_BLOG_AUTHOR = "Mehmood";
	private static final String CREATE_BLOG_CONTENT = "Java is an awesome programming lanaguage.";

	private static final String CREATE_CATEGORY_NAME = GET_POSTS_BY_CATEGORY_CATNAME;

	private static final String API_CREATE_CATEGORY = "/jeeassignment4/rest/blog/newcategory";
	private static final String API_GET_ALL_CATEGORIES = "/jeeassignment4/rest/blog/categories";
	private static final String API_CREATE_BLOG_POSTS = "/jeeassignment4/rest/blog/newpost";
	private static final String API_UPDATE_BLOG_POSTS = "/jeeassignment4/rest/blog/updatepost";
	private static final String API_GET_ALL_BLOG_POSTS = "/jeeassignment4/rest/blog/posts";
	private static final String API_DELETE_BLOG_POSTS = "/jeeassignment4/rest/blog/deletepost";
	private static final String API_GET_BLOG_POSTS_BY_CAT = "/jeeassignment4/rest/blog/postsbycategory";
	private static final String API_GET_BLOG_POSTS_BY_AUTHOR = "/jeeassignment4/rest/blog/postsbyauthor";
	private static final String API_GET_COMMETNS_ON_BLOG_POST = "/jeeassignment4/rest/blog/comments";
	private static final String API_ADD_COMMETNS_ON_BLOG_POST = "/jeeassignment4/rest/blog/addcomment ";
	private static final String API_DELETE_COMMETNS = "/jeeassignment4/rest/blog/deletecomment";

	private static final String API_DELETE_USER = "/jeeassignment4/rest/user/deleteuser";
	private static final String API_DELETE_USERROLE = "/jeeassignment4/rest/user/deleteuserrole";

	private static final String API_CREATE_USER = "/jeeassignment4/rest/user/newuser";
	private static final String API_CREATE_USERROLE = "/jeeassignment4/rest/user/newuserrole";
	private static final String API_CREATE_ROLE = "/jeeassignment4/rest/user/newrole";

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void testCreateNewCategory() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;
		Category category = new Category();
		category.setCategoryName(CREATE_CATEGORY_NAME);

		Response response = request.given().contentType(ContentType.JSON).body(category).post(API_CREATE_CATEGORY)
				.then().statusCode(200).extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals("testCreateNewCategory", 200, statusCode);

	}

	@Test
	public void testGetAllCategories() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Category> list = new ArrayList<>();
		list = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list.getClass());
		int size = list.size();
		Assert.assertNotNull(list);
		Assert.assertTrue(size > 0);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateNewPost() {

		RestAssured.defaultParser = Parser.JSON;
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list1.getClass());
		CREATE_BLOG_CATEGORYID = getIdFromResult(list1);

		Category category = new Category();
		category.setId(CREATE_BLOG_CATEGORYID);

		BlogPost blogPost = new BlogPost();
		blogPost.setCategory(category);
		blogPost.setContent(CREATE_BLOG_CONTENT);
		blogPost.setAuthorName(CREATE_BLOG_AUTHOR);
		blogPost.setTitle(CREATE_BLOG_TITLE);

		Response response = request.given().contentType(ContentType.JSON).body(blogPost).post(API_CREATE_BLOG_POSTS)
				.then().statusCode(200).extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals(200, statusCode);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllBlogPosts() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list = new ArrayList<>();
		list = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list.getClass());
		int size = list.size();
		Assert.assertNotNull(list);
		Assert.assertTrue(size > 0);

	}

	@Test
	public void testUpdateBlogPost() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list1.getClass());
		UPDATE_BLOGID = getIdFromResult(list1);

		List<Map<String, Object>> list2 = new ArrayList<>();
		list2 = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list2.getClass());
		CREATE_BLOG_CATEGORYID = getIdFromResult(list2);

		CategoryDTO category = new CategoryDTO();
		category.setId(CREATE_BLOG_CATEGORYID);

		BlogPostDTO blogPost = new BlogPostDTO();
		blogPost.setId(UPDATE_BLOGID);
		blogPost.setCategory(category);
		blogPost.setContent(UPDATE_BLOG_CONTENT);
		blogPost.setAuthorName(UPDATE_BLOG_AUTHOR);
		blogPost.setTitle(UPDATE_BLOG_TITLE);

		Response response = request.given().contentType(ContentType.JSON).body(blogPost)
				.put(API_UPDATE_BLOG_POSTS + "/1").then().statusCode(200).extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals(200, statusCode);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPostsByCategory() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<BlogPost> list = new ArrayList<>();
		list = request.given().when().get(API_GET_BLOG_POSTS_BY_CAT + "/" + GET_POSTS_BY_CATEGORY_CATNAME).then()
				.extract().body().as(list.getClass());
		int size = list.size();
		Assert.assertNotNull(list);
		Assert.assertTrue(size > 0);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPostsByAuthor() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<BlogPost> list = new ArrayList<>();
		list = request.given().when().get(API_GET_BLOG_POSTS_BY_AUTHOR + "/" + GET_POSTS_BY_AUTHOR_AUTHNAME).then()
				.extract().body().as(list.getClass());
		int size = list.size();
		Assert.assertNotNull(list);
		Assert.assertTrue(size > 0);

	}

	@Test
	public void testAddNewComment() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list1.getClass());
		UPDATE_BLOGID = getIdFromResult(list1);

		List<Map<String, Object>> list2 = new ArrayList<>();
		list2 = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list2.getClass());
		CREATE_BLOG_CATEGORYID = getIdFromResult(list2);

		CategoryDTO category = new CategoryDTO();
		category.setId(CREATE_BLOG_CATEGORYID);

		BlogPostDTO blogPost = new BlogPostDTO();
		blogPost.setId(UPDATE_BLOGID);
		blogPost.setCategory(category);
		blogPost.setContent(CREATE_BLOG_CONTENT);
		blogPost.setAuthorName(CREATE_BLOG_AUTHOR);
		blogPost.setTitle(CREATE_BLOG_TITLE);

		CommentDTO comment = new CommentDTO();
		comment.setCommentby(ADD_NEW_COMMENT_CONTENTBY);
		comment.setContent(ADD_NEW_COMMENT_CONTENT);
		comment.setPost(blogPost);

		Response response = request.given().contentType(ContentType.JSON).body(comment)
				.post(API_ADD_COMMETNS_ON_BLOG_POST).then().statusCode(200).extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals(200, statusCode);

	}

	private int getIdFromResult(List<Map<String, Object>> list) {
		Map<String, Object> map = list.get(0);
		Double bigInteger = new Double(map.get("id").toString());
		return bigInteger.intValue();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCommentsOnPost() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list1.getClass());

		UPDATE_BLOGID = getIdFromResult(list1);
		GET_COMMENTS_ONPOST_BLOGID = getIdFromResult(list1);

		List<Map<String, Object>> list2 = new ArrayList<>();
		list2 = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list2.getClass());
		CREATE_BLOG_CATEGORYID = getIdFromResult(list2);

		CategoryDTO category = new CategoryDTO();
		category.setId(CREATE_BLOG_CATEGORYID);

		BlogPostDTO blogPost = new BlogPostDTO();
		blogPost.setId(UPDATE_BLOGID);
		blogPost.setCategory(category);
		blogPost.setContent(CREATE_BLOG_CONTENT);
		blogPost.setAuthorName(CREATE_BLOG_AUTHOR);
		blogPost.setTitle(CREATE_BLOG_TITLE);

		CommentDTO comment = new CommentDTO();
		comment.setCommentby(ADD_NEW_COMMENT_CONTENTBY);
		comment.setContent(ADD_NEW_COMMENT_CONTENT);
		comment.setPost(blogPost);

		request.given().contentType(ContentType.JSON).body(comment).post(API_ADD_COMMETNS_ON_BLOG_POST).then()
				.statusCode(200).extract().response();

		List<Map<String, Object>> list = new ArrayList<>();
		RequestSpecification request1 = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;
		list = request1.contentType(ContentType.JSON).given().when()
				.get(API_GET_COMMETNS_ON_BLOG_POST + "/" + GET_COMMENTS_ONPOST_BLOGID).then().extract().body()
				.as(list.getClass());
		int size = list.size();
		Assert.assertNotNull(list);
		Assert.assertTrue(size > 0);

	}

	@Test
	public void testDeleteComment() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list1.getClass());

		UPDATE_BLOGID = getIdFromResult(list1);
		GET_COMMENTS_ONPOST_BLOGID = getIdFromResult(list1);

		List<Map<String, Object>> list2 = new ArrayList<>();
		list2 = request.given().when().get(API_GET_ALL_CATEGORIES).then().extract().body().as(list2.getClass());
		CREATE_BLOG_CATEGORYID = getIdFromResult(list2);

		CategoryDTO category = new CategoryDTO();
		category.setId(CREATE_BLOG_CATEGORYID);

		BlogPostDTO blogPost = new BlogPostDTO();
		blogPost.setId(UPDATE_BLOGID);
		blogPost.setCategory(category);
		blogPost.setContent(CREATE_BLOG_CONTENT);
		blogPost.setAuthorName(CREATE_BLOG_AUTHOR);
		blogPost.setTitle(CREATE_BLOG_TITLE);

		CommentDTO comment = new CommentDTO();
		comment.setCommentby(ADD_NEW_COMMENT_CONTENTBY);
		comment.setContent(ADD_NEW_COMMENT_CONTENT);
		comment.setPost(blogPost);

		request.given().contentType(ContentType.JSON).body(comment).post(API_ADD_COMMETNS_ON_BLOG_POST).then()
				.statusCode(200).extract().response();

		RequestSpecification request1 = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list4 = new ArrayList<>();
		list4 = request1.given().when().get(API_GET_COMMETNS_ON_BLOG_POST + "/" + GET_COMMENTS_ONPOST_BLOGID).then()
				.extract().body().as(list4.getClass());

		DELETE_COMMENT_ID = getIdFromResult(list4);

		Response response = request1.delete(API_DELETE_COMMETNS + "/" + DELETE_COMMENT_ID).then().statusCode(200)
				.extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals(200, statusCode);

	}

	@Test
	public void testDeleteBlogPost() {
		RequestSpecification request = RestAssured.given().auth().basic(USERNAME_TEST_ADMIN, TEST_PASSWORD);
		RestAssured.defaultParser = Parser.JSON;

		List<Map<String, Object>> list1 = new ArrayList<>();
		list1 = request.given().when().get(API_GET_ALL_BLOG_POSTS).then().extract().body().as(list1.getClass());

		DELETE_BLOG_ID = getIdFromResult(list1);

		Response response = request.delete(API_DELETE_BLOG_POSTS + "/" + DELETE_BLOG_ID).then().statusCode(200)
				.extract().response();
		int statusCode = response.statusCode();
		Assert.assertEquals(200, statusCode);

	}
}