package com.codesnippet.weather_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codesnippet.weather_service.repository.UserDetailsRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userDetailsRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username + " not found"));
    }
    
}
