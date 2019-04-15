/********************************************************************egg***m******a**************n************

 * 
 * File: UserResource.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/
package com.algonquincollege.cst8277.controller;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.dao.SimpleBean;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;
import com.algonquincollege.cst8277.models.PlatformUserRole;

@Path("/user")
public class UserResource {

	@EJB
	private SimpleBean simpleBean;

	/**
	 * @param platformRole
	 * @return new role 
	 */
	@POST
	@Path("/newrole")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRole(PlatformRole platformRole) {
		simpleBean.createRole(platformRole);
		return Response.ok().build();
	}

	/**
	 * @param platformUser
	 * @return user created
	 */
	@POST
	@Path("/newuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(PlatformUser platformUser) {
		simpleBean.createUser(platformUser);
		return Response.ok().build();
	}

	/**
	 * @param platformUserRole
	 * @return new user role
	 */
	@POST
	@Path("/newuserrole")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUserRole(PlatformUserRole platformUserRole) {
		simpleBean.createUserRole(platformUserRole);
		return Response.ok().build();
	}
	
	/**
	 * @param platformUserRole
	 * @return delete user role
	 */
	@POST
	@Path("/deleteuserrole")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserRole(PlatformUserRole platformUserRole) {
		simpleBean.deleteUserRole(platformUserRole);
		return Response.ok().build();
	}
	
	/**
	 * @param username
	 * @return delete user
	 */
	@POST
	@Path("/deleteuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(String username) {
		simpleBean.deleteUser(username);
		return Response.ok().build();
	}
	

}
