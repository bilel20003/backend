package com.centre.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centre.service.model.Personne;
import com.centre.service.model.Role;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
	boolean existsByEmail(String email);
	List<Personne> findByRole(Role role);

}
