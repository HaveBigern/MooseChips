package com.strath.visu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strath.visu.domain.DataUser;

/**
 * Spring Data JPA repository for the DataUser entity.
 */
public interface DataUserRepository extends JpaRepository<DataUser,Long> {

}
