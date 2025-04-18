package com.authService.authService.repositories;

//#region imports
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//#endregion

import com.authService.authService.models.Role;
import com.authService.authService.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    List<User> findBySurName(String surName);
    List<User> findByRole(Role rol);
}