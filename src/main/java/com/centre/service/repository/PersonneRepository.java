package com.centre.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centre.service.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
	boolean existsByEmail(String email);
}
