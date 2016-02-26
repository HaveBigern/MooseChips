package com.strath.visu.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strath.visu.domain.Type;

/**
 * Spring Data JPA repository for the Type entity.
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

	@Modifying
	@Transactional 
	@Query("UPDATE Type t SET t.averageRoute = NULL WHERE t.typeId = :typeId")
	public void deleteAvgRoute(@Param("typeId") Long id);
	
}
