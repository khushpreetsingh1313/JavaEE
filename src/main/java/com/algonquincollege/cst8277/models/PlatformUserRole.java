/********************************************************************egg***m******a**************n************

 * File: PlatformUserRole.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PLATFORM_USER_ROLE")
public class PlatformUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "rolename")
	private String rolename;

	@Column(name = "username")
	private String username;

	/**
	 * super constructor
	 */
	public PlatformUserRole() {
		super();
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
