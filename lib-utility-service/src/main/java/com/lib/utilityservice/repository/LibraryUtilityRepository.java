package com.lib.utilityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.utilityservice.model.LibraryUtility;

public interface LibraryUtilityRepository extends JpaRepository<LibraryUtility, Long> {

	Optional<LibraryUtility> findByMemberId(Long id);

}
