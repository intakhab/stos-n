package com.hcl.usf.repo;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.usf.domain.RegisterTC;
/**
  @author intakhabalam.s@hcl.com
 */
@Repository
public interface RegisterTCRepo extends CrudRepository<RegisterTC, Long>{
	/***
	 * @param tcName {@link String}
	 * @return link {@link RegisterTC}
	 */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from RegisterTC t where t.tcName=?1")
	RegisterTC findByTestName(String tcName);
	
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from RegisterTC t order by t.id desc")
	public Iterable<RegisterTC> findAll();


}
