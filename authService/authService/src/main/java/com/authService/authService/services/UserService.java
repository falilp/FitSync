package com.authService.authService.services;

//#region imports
import java.util.Optional;
import com.authService.authService.models.User;
import com.authService.authService.repositories.UserRepository;
//#endregion

public class UserService{
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){ this.userRepo = userRepo; }

    public Optional<User> loadUserByUserName(String userName){ return userRepo.findByUserName(userName); }
}