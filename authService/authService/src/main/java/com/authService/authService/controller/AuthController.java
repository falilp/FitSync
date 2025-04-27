package com.authService.authService.controller;

//#region imports
import com.authService.authService.dto.AuthRequest;
import com.authService.authService.dto.AuthResponse;
import com.authService.authService.dto.RegisterRequest;
import com.authService.authService.dto.ResgisterResponse;
import com.authService.authService.services.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//#endregion

@RestController
@RequestMapping("/auth")
public class AuthController{
    private final AuthService authServ;

    @Autowired
    public AuthController(AuthService serv){ this.authServ = serv; }

    @GetMapping("/register")
    public ResgisterResponse register(@RequestBody RegisterRequest req){ return authServ.register(req); }

    @GetMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req){ return authServ.login(req); }
}