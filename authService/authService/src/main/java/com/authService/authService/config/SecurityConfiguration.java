package com.authService.authService.config;

//#region imports
import com.authService.authService.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//#endregion

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    private final UserService userServ;
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfiguration(UserService userServ, JwtAuthenticationFilter jwtFilter){
        this.userServ = userServ;
        this.jwtFilter = jwtFilter;
    }
}