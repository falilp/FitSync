package com.authService.authService.services;

//#region imports
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
// OR
//import java.util.Base64;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//#endregion

@Service
public class JwtService{
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration-time}")
    private long expTime;

    public String buildToken(Map<String, Object> claims, String userName){ 
        if(claims == null) claims = new HashMap<String, Object>();

        return generateToken(claims, userName);
    }

    private Key getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        //byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> claims, String userName){
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expTime))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public Boolean validateToken(String token, String userName){
        String name = extractUserName(token);
        return userName.equals(name) && !isExpirated(token);
    }

    private Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <tValue>tValue extractClaim(String token, Function<Claims,tValue> claimsResolver){
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token){ return extractClaim(token, Claims::getSubject); }

    public Date extractIssuedAt(String token){ return extractClaim(token, Claims::getIssuedAt); }
    
    public Date extractExpiration(String token){ return extractClaim(token, Claims::getExpiration); }
    
    public Boolean isExpirated(String token){ return extractExpiration(token).before(new Date()); }

    public long expirationTime(){ return expTime; }
}