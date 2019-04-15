/********************************************************************egg***m******a**************n************

 * File: ModelBase.java
 * Course materials (19W) CST 8277
 * @author Gursewak Singh
 * @author Khushpreet Singh
 * @author Ravanpreet Singh
 * @author Amanpreet Singh
 * 
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract class that is base of (class) hierarchy for all com.algonquincollege.cst8277.models @Entity classes
 */
@MappedSuperclass
public abstract class ModelBase implements Auditable {

    protected int id;
    protected int version;
    protected Audit audit = new Audit();
    
    public ModelBase() {
    	super();
    	this.audit.setCreatedDate(LocalDateTime.now());
    	this.audit.setUpdatedDate(this.audit.getCreatedDate());
	}

    /**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * @return version
     */
    @Version
    public int getVersion() {
        return version;
    }
    /**
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }
	/**
	 * @return the audit
	 */
	public Audit getAudit() {
		return audit;
	}
	/**
	 * @param audit the audit to set
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
    
}