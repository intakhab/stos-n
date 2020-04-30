package com.hcl.usf.repo;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.usf.domain.TCSettings;

@Repository
public interface TCSettingsRepo extends CrudRepository<TCSettings, Long>{
	/**
	 * @return {@link Iterable}
	 */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from TCSettings t")
	Iterable<TCSettings> findAllSettings();
	
}

