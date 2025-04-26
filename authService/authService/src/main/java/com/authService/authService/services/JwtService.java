package com.authService.authService.services;

//#region imports
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//#endregion

@Service
public class JwtService{
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration-time}")
    private long expTime;

    public long expirationTime(){ return expTime; }
}