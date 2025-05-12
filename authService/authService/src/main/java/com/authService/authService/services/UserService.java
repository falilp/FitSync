package com.authService.authService.services;

//#region imports
import org.springframework.stereotype.Service;
import com.authService.authService.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//#endregion

@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){ this.userRepo = userRepo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{ 
        return userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("El nombre de usuario: '" + username + "' no se encontró")); 
    }
}