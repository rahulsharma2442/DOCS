package com.codesnippet.weather_service.entity;

import lombok.Data;

@Data
public class AuthRequest {
    
    private String username;
    private String password;

}
