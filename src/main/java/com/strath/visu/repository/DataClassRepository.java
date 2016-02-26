package com.strath.visu.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strath.visu.domain.DataClass;

/**
 * Spring Data JPA repository for the DataClass entity.
 */
public interface DataClassRepository extends JpaRepository<DataClass,Long> {

	@Query("select dc from DataClass dc where dc.route.route_id = :routeId")
	public List<DataClass> getDataClassByRoute(@Param("routeId") Long id);
	
	@Modifying
	@Transactional 
	@Query("delete from DataClass dc where dc.route.route_id = :routeId")
	public void deleteDataClassByRoute(@Param("routeId") Long id);
	
	@Query("select dc from DataClass dc where dc.route.type = :type")
	public List<DataClass> getDataClassByExperimentType(@Param("type") Integer type);
	
}
