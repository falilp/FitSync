package com.authService.authService.services;

//#region imports
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//#endregion

@Service
public class JwtService{
    @Value("${Security.jwt.secret-key}")
    private String secretKey;
    @Value("${Security.jwt.expiration-time}")
    private long expTime;
}