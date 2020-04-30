package com.hcl.usf.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
@Entity
@Table(name = "GROUP_SUITE_TC")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GroupSuiteTC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TC_NAME")
	private String tcName;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GROUP_SUITE_ID", nullable = false,updatable=true)
	private GroupSuite groupSuite;


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

	public GroupSuite getGroupSuite() {
		return groupSuite;
	}

	public void setGroupSuite(GroupSuite groupSuite) {
		this.groupSuite = groupSuite;
	}
   
	
}
