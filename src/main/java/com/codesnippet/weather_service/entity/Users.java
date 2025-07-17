package com.codesnippet.weather_service.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users implements UserDetails {
 
   @Id
   @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   private String role;
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
    return 
        java.util.Collections.singleton(() -> role);
   }
}
