package com.strath.visu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strath.visu.domain.Route;

/**
 * Spring Data JPA repository for the Route entity.
 */
public interface RouteRepository extends JpaRepository<Route,Long> {

	@Query("select rt from Route rt where rt.type = :type")
	public List<Route> getByExperimentType(@Param("type") Integer type);
	
}
