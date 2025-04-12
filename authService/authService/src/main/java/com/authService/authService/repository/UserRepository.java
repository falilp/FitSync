package com.authService.authService.repository;

//#region imports
import java.util.List;
import java.util.Optional;
import com.authService.authService.model.User;
import com.authService.authService.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
//#endregion

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    List<User> findBySurName(String surName);
    List<User> findByRole(Role rol);
}