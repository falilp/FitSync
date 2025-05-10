package com.authService.authService.config;

//#region imports
import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.authService.authService.models.User;
import com.authService.authService.services.JwtService;
import com.authService.authService.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.security.core.context.SecurityContextHolder;
//#endregion

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JwtService jwtServ;
    @Autowired
    private UserService userServ;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        final String authHeader = request.getHeader("Authorization");

        //Al empezar la cabecera con Bearer se ignora
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        try{
            final String jwt = authHeader.substring(7);
            final String userName = jwtServ.extractUserName(jwt);

            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //Optional<User> user = userServ.loadUserByUserName(userName);
            }

        }catch(Exception excep){ handlerExceptionResolver.resolveException(request, response, null, excep); }
    }
}