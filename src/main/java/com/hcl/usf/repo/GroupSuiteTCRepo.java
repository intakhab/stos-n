package com.hcl.usf.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.hcl.usf.domain.GroupSuiteTC;
/***
 * @author intakhabalam.s@hcl.com
 */
public interface GroupSuiteTCRepo extends CrudRepository<GroupSuiteTC, Long> {
    /***
     * 
     * @param groupId  {@link Long}
     * @return {@link List}
     */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from GroupSuiteTC t where t.groupSuite.id=?1")
	List<GroupSuiteTC> findBygroupId(Long groupId);
	/**
	 * 
	 * @param groupName  {@link String}
	 * @return  {@link List}
	 */
	@Query("select t from GroupSuiteTC t where t.groupSuite.groupName=?1")
	public List<GroupSuiteTC> findBygroupName(String groupName);
}
