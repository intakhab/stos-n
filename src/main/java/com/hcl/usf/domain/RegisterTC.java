package com.hcl.usf.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
@Entity
@Table(name = "REGISTER_TC")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class RegisterTC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "TC_NAME",unique = true)
	private String tcName = "";
	@Column(name = "TC_STORY")
	private String tcStory;
	@Column(name = "TC_SHEET_NAME")
	private String tcSheetName = "";
	@Column(name = "TC_VERSION")
	private String tcVersion = "";
	@Column(name = "TC_REG_DATE")
	private String tcRegDate = "";
	@Column(name = "TC_REG_NOTE")
	private String tcRegNote;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getTcSheetName() {
		return tcSheetName;
	}

	public void setTcSheetName(String tcSheetName) {
		this.tcSheetName = tcSheetName;
	}

	public String getTcVersion() {
		return tcVersion;
	}

	public void setTcVersion(String tcVersion) {
		this.tcVersion = tcVersion;
	}

	public String getTcRegDate() {
		return tcRegDate;
	}

	public void setTcRegDate(String tcRegDate) {
		this.tcRegDate = tcRegDate;
	}

	public String getTcStory() {
		return tcStory;
	}

	public void setTcStory(String tcStory) {
		this.tcStory = tcStory;
	}

	public String getTcRegNote() {
		return tcRegNote;
	}

	public void setTcRegNote(String tcRegNote) {
		this.tcRegNote = tcRegNote;
	}

}
