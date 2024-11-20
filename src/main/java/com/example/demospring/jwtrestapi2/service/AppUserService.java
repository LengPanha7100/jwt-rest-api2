package com.example.demospring.jwtrestapi2.service;

import com.example.demospring.jwtrestapi2.model.AppUser;
import com.example.demospring.jwtrestapi2.model.dto.request.AppUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
//UserDetailsService get user from repo and take into spring security
public interface AppUserService extends UserDetailsService {
    AppUser register(AppUserRequest appUserRequest);
    Integer getUsernameOfCurrentUser();
}