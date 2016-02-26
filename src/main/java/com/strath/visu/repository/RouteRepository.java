package com.strath.visu.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strath.visu.domain.Route;

/**
 * Spring Data JPA repository for the Route entity.
 */
public interface RouteRepository extends JpaRepository<Route,Long> {

	@Query("select rt from Route rt where rt.type.typeId = :type")
	public List<Route> getByExperimentType(@Param("type") Long type);
	
	@Modifying
	@Transactional 
	@Query("delete from Route rt where rt.route_id = :routeId")
	public void deleteRoute(@Param("routeId") Long id);
}
