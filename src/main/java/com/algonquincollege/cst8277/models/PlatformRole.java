/********************************************************************egg***m******a**************n************

 * 
 * File: PlatformRole.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PLATFORM_ROLE")
public class PlatformRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "rolename")
	private String rolename;

	/**
	 * super constructor
	 */
	public PlatformRole() {
		super();
	}

	/**
	 * @return id
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

}
