package com.hcl.usf.repo;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.hcl.usf.domain.GroupSuite;
/***
 * @author intakhabalam.s@hcl.com
 */
public interface GroupSuiteRepo  extends CrudRepository<GroupSuite, Long>{
	/**
	 * @param groupName {@link String}
	 * @return {@link GroupSuite}
	 */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from GroupSuite t where t.groupName=?1")
	GroupSuite findBygroupName(String groupName);
	
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from GroupSuite t")
	Iterable<GroupSuite> findAll();

}
