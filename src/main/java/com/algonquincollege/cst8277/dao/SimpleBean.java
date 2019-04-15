/********************************************************************egg***m******a**************n************

 * 
 * File: SimpleBean.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.algonquincollege.cst8277.dto.BlogPostDTO;
import com.algonquincollege.cst8277.dto.CategoryDTO;
import com.algonquincollege.cst8277.dto.CommentDTO;
import com.algonquincollege.cst8277.models.BlogPost;
import com.algonquincollege.cst8277.models.Category;
import com.algonquincollege.cst8277.models.Comment;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;
import com.algonquincollege.cst8277.models.PlatformUserRole;

@Stateless
public class SimpleBean {

	@PersistenceContext(unitName = "testdb")
	protected EntityManager em;

	/**
	 * Constructor
	 */
	public SimpleBean() {
	}

	@SuppressWarnings("unchecked")
	public List<BlogPost> getAllPosts() {
		Query q = em.createQuery("select t from BlogPost t");
		return q.getResultList();
	}
	
	/**
	 * @param authorName
	 * @return resultlist
	 */
	public List<BlogPost> getAllPostsByAuthor(String authorName) {
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);
			Root<BlogPost> rootEntry = cq.from(BlogPost.class);
			cq.where(cb.equal(rootEntry.get("authorName"), authorName));
			TypedQuery<BlogPost> q = em.createQuery(cq);
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param categoryId
	 * @return resultlist
	 */
	public List<BlogPost> getAllPostsByCategory(String categoryId) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<BlogPost> blogQuery = cb.createQuery(BlogPost.class);
			Root<BlogPost> root = blogQuery.from(BlogPost.class);

			Subquery<Category> subquery = blogQuery.subquery(Category.class);
			Root<BlogPost> subroot = subquery.correlate(root);
			Join<BlogPost, Category> category = subroot.join("category");

			subquery.select(category);
			subquery.where(cb.equal(category.get("categoryName"), categoryId));

			blogQuery.where(cb.exists(subquery));

			TypedQuery<BlogPost> typedQuery = em.createQuery(blogQuery);
			List<BlogPost> resultList = typedQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param tag
	 * @return resultlist
	 */
	public List<BlogPost> getAllPostsByTag(String tag) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);
			Root<BlogPost> rootEntry = cq.from(BlogPost.class);
			cq.where(cb.equal(rootEntry.get("id"), tag));
			TypedQuery<BlogPost> q = em.createQuery(cq);
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param postId
	 * @return resultlist
	 */
	public List<Comment> getAllCommentsOnPost(Integer postId) {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Comment> blogQuery = cb.createQuery(Comment.class);
			Root<Comment> root = blogQuery.from(Comment.class);

			Subquery<BlogPost> subquery = blogQuery.subquery(BlogPost.class);
			Root<Comment> subroot = subquery.correlate(root);
			Join<Comment, BlogPost> post = subroot.join("post");

			subquery.select(post);
			subquery.where(cb.equal(post.get("id"), new Integer(postId)));

			blogQuery.where(cb.exists(subquery));

			TypedQuery<Comment> typedQuery = em.createQuery(blogQuery);
			List<Comment> resultList = typedQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param post
	 * @return a message "Done" after creating post
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String createNewPost(BlogPost post) {
		try {
			em.persist(post);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param id
	 * @return  a message "Done" after deleting post
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String deletePost(int id) {
		try {
			BlogPost blogPost = em.find(BlogPost.class, id);
			List<Comment> allCommentsOnPost = getAllCommentsOnPost(id);
			allCommentsOnPost.forEach(p -> em.remove(p));
			em.remove(blogPost);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param dto
	 * @return  a message "Done" after comment
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String addNewComment(CommentDTO dto) {
		try {
			Integer postId = dto.getPost().getId();
			BlogPost post = em.find(BlogPost.class, postId);
			Comment comment = new Comment();
			comment.setContent(dto.getContent());
			comment.setPost(post);
			em.persist(comment);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param updatedBlogPost
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateBlogPost(BlogPostDTO updatedBlogPost) {
		try {
			BlogPost find = em.find(BlogPost.class, updatedBlogPost.getId());
			find.setAuthorName(updatedBlogPost.getAuthorName());
			find.setContent(updatedBlogPost.getContent());
			find.setTitle(updatedBlogPost.getTitle());

			CategoryDTO newCatDto = updatedBlogPost.getCategory();
			Integer newCatId = newCatDto.getId();

			Category cat = null;
			if (newCatId == null) {
				cat = new Category();
				cat.setCategoryName(newCatDto.getCategoryName());
			} else {
				cat = em.find(Category.class, newCatId);
			}
			find.setCategory(cat);

			em.merge(find);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param category
	 * @return  a message "Done" after creating category
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String createNewCategory(Category category) {
		try {
			em.persist(category);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @return resultlist
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		try {
			Query q = em.createQuery("select c from Category c");
			return q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param id
	 * @return  a message "Done" after comment 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String deleteComment(int id) {
		try {
			Comment comment = em.find(Comment.class, id);
			em.remove(comment);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param platformUser
	 * @return  a message "Done"
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String createUser(PlatformUser platformUser) {
		try {
			em.persist(platformUser);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * @param platformRole
	 * @return  a message "Done"
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String createRole(PlatformRole platformRole) {
		try {
			em.persist(platformRole);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * @param platformUserRole
	 * @return  a message "Done"
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String createUserRole(PlatformUserRole platformUserRole) {
		try {
			em.persist(platformUserRole);
			return "Done";
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param platformUserRole
	 * @return a message "Done" after deleting user
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String deleteUserRole(PlatformUserRole platformUserRole) {
		try {
			Query q = em.createQuery("delete ur from PlatformUserRole ur where ur.username = :pUsername and ur.rolename=:pRolename");
			q.setParameter("pUsername", platformUserRole.getUsername());
			q.setParameter("pRolename", platformUserRole.getRolename());			
			q.executeUpdate();
			return "Done";
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * @param username
	 * @return a message "Done" after updating username
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String deleteUser(String username) {
		try {
			Query q = em.createQuery("delete ur from PlatformUser u where u.username = :pUsername");
			q.setParameter("pUsername", username);			
			q.executeUpdate();
			return "Done";
		} catch (Exception e) {
			throw e;
		}
		
	}
}
