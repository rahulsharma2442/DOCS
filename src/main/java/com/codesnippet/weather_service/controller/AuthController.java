package com.codesnippet.weather_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codesnippet.weather_service.entity.AuthRequest;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }
        catch(Exception e) {
            throw new RuntimeException("Invalid username or password");
        }
        return "GeneratedToken"; 
    }
}
