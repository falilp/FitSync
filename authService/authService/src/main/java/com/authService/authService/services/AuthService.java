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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//#endregion

@Service
public class AuthService{
    @Autowired
    private JwtService jwtServ;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired
    private AuthenticationManager authManager;

    // Eviatmos el consttrustor gracias a la inyección de dependencias del @AutoWired
    /*public AuthService(JwtService jwt, UserRepository repo, PasswordEncoder Encoder, AuthenticationManager manager){
        this.jwt = jwt;
        this.userRepo = repo;
        this.passEncoder = Encoder;
        this.authManager = manager;
    }*/

    public ResgisterResponse register(RegisterRequest req){
        User user = new User();
        
        user.setUserName(req.getUsername());
        user.setPassword(passEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setSurName(req.getSurName());
        user.setEmail(req.getEmail());
        user.setBirthDate(req.getBirthDate());
        user.setRole(req.getRol()); 
        user.setGender(req.getGender());
        user.setHeight(req.getHeight());
        user.setWeight(req.getWeight());
    
        userRepo.save(user);

        String token = jwtServ.buildToken(null, user.getUsername());

        return new ResgisterResponse("Exito al crear el usuario", token);
    }

    public AuthResponse login(AuthRequest req){
        //Comprueba que el username y password son válidos
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        //Guarda la sesión autenticada en Spring Security
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = userRepo.findByUserName(req.getUsername()).orElseThrow(() -> new RuntimeException("El usuario" + req.getUsername() +" no existe"));

        String token = jwtServ.buildToken(null, user.getUsername());

        return new AuthResponse(token);
    }
}