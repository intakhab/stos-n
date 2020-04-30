package com.hcl.usf.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hcl.usf.domain.ReportsTC;
/***
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
@Repository
public interface ReportsTCRepo extends PagingAndSortingRepository <ReportsTC, Long> {
    /**
     * 
     * @param pa {@link PageRequest}
     * @return {@link List} of reports
     */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	@Query("select t from ReportsTC t order by t.id DESC")
	List<ReportsTC>  loadReportsTC(PageRequest pa);

}
