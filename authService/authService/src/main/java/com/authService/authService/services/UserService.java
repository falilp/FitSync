package com.authService.authService.services;

//#region imports
import com.authService.authService.repositories.UserRepository;
//#endregion

public class UserService{
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){ this.userRepo = userRepo; }
}