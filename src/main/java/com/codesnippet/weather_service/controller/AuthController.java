package com.codesnippet.weather_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codesnippet.weather_service.entity.AuthRequest;
import com.codesnippet.weather_service.util.JWTUtil;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest){
        try{
            // Authenticate the user
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            // If authentication is successful, generate and return the JWT token
            // The JWTUtil class should have a method to generate the token
            return jwtUtil.generateToken(authRequest.getUsername());
        }
        catch(Exception e) {
            throw new RuntimeException("Invalid username or password");
        }
        
    }
}
