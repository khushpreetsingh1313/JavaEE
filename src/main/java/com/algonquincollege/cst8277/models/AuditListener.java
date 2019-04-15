/********************************************************************egg***m******a**************n************

 * File: AuditListener.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * 
*/
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

	/**
	 * @param auditable
	 */
	@PrePersist
	public void setCreatedDate(Auditable auditable) {
		Audit audit = auditable.getAudit();
		audit.setCreatedDate(LocalDateTime.now());
	}
	
	/**
	 * @param auditable
	 */
	@PreUpdate
	public void setUpdatedDate (Auditable auditable) {
	Audit audit = auditable.getAudit();
	audit.setUpdatedDate(LocalDateTime.now());
	}
}
