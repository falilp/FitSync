package com.authService.authService.repositories;

//#region imports
import java.util.List;
import java.util.Optional;
import com.authService.authService.models.Role;
import com.authService.authService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
//#endregion

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String username);
    List<User> findByName(String name);
    List<User> findBySurName(String surName);
    List<User> findByRole(Role rol);
}