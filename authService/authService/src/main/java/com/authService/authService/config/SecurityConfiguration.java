package com.authService.authService.config;

//#region imports
import com.authService.authService.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//#endregion

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    private UserService userServ;
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    // Eviatmos el consttrustor gracias a la inyección de dependencias del @AutoWired
    /*public SecurityConfiguration(UserService userServ, JwtAuthenticationFilter jwtFilter){
        this.userServ = userServ;
        this.jwtFilter = jwtFilter;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{ return config.getAuthenticationManager(); }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService((UserDetailsService) userServ);
        authProvider.setPasswordEncoder(passEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passEncoder(){ return new BCryptPasswordEncoder(); }
}