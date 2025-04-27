package com.authService.authService.services;

//#region imports
import org.springframework.stereotype.Service;
import com.authService.authService.models.User;
import com.authService.authService.dto.AuthRequest;
import com.authService.authService.dto.AuthResponse;
import com.authService.authService.dto.RegisterRequest;
import com.authService.authService.dto.ResgisterResponse;
import com.authService.authService.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//#endregion

@Service
public class AuthService{
    private final JwtService jwt;
    private final UserRepository userRepo;
    private final PasswordEncoder passEncoder;
    private final AuthenticationManager authManager;

    public AuthService(JwtService jwt, UserRepository repo, PasswordEncoder Encoder, AuthenticationManager manager){
        this.jwt = jwt;
        this.userRepo = repo;
        this.passEncoder = Encoder;
        this.authManager = manager;
    }

    public ResgisterResponse register(RegisterRequest req){
        User user = new User();
        
        user.setUserName(req.getUsername());
        user.setPassword(passEncoder.encode(req.getPassword()));
        
        userRepo.save(user);

        return new ResgisterResponse("Exito al crear el usuario");
    }

    public AuthResponse login(AuthRequest req){
        //Comprueba que el username y password son válidos
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        //Guarda la sesión autenticada en Spring Security
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = userRepo.findByUserName(req.getUsername()).orElseThrow(() -> new RuntimeException("El usuario" + req.getUsername() +" no existe"));

        String token = jwt.buildToken(null, user.getUsername());

        return new AuthResponse(token);
    }
}