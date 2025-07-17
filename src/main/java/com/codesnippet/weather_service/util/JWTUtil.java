package com.codesnippet.weather_service.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JWTUtil {
    
    public String generateToken(String username) {
        // Logic to generate JWT token
        final String SECRET_KEY = "my-super-secret-key@random2442!!";
        long expirationMillis = System.currentTimeMillis() + 1000 * 60 * 10; // Token valid for 10 minutes
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
        Date issuedAt = new Date();
        Date expiration = new Date(expirationMillis);
        String token = Jwts.builder()
            .claim("sub", username)
            .claim("iat", issuedAt)
            .claim("exp", expiration)
            .signWith(key)
            .compact();
        return token;
    }
}
