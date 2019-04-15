/********************************************************************egg***m******a**************n************

 * 
 * File: Audit.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 *
*/

package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit {

	protected LocalDateTime createdDate;
	protected LocalDateTime updatedDate;
	
	/**
	 * @return created date
	 */
	@Column(name = "CREATED_DATE")
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param createdDate
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * @return updated date
	 */
	@Column(name = "UPDATED_DATE")
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate
	 */
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
