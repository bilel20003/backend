package com.centre.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centre.service.model.UserInfo;
import com.centre.service.model.Role;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    boolean existsByEmail(String email);

    Optional<UserInfo> findByEmail(String email); // Ajout pour l'authentification

    List<UserInfo> findByRole(Role role);

    List<UserInfo> findByNomContainingIgnoreCase(String nom); // Recherche par nom sans tenir compte de la casse

    List<UserInfo> findAllByRole(Role role);
}
