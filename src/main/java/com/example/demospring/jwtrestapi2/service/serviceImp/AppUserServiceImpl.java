package com.example.demospring.jwtrestapi2.service.serviceImp;

import com.example.demospring.jwtrestapi2.model.AppUser;
import com.example.demospring.jwtrestapi2.model.dto.request.AppUserRequest;
import com.example.demospring.jwtrestapi2.repository.AppUserRepository;
import com.example.demospring.jwtrestapi2.service.AppUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    //get user from database into spring security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser register(AppUserRequest appUserRequest) {
        return appUserRepository.register(appUserRequest);
    }

    @Override
    public Integer getUsernameOfCurrentUser() {
        AppUser userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
//        System.out.println(userDetails);
        return userDetails.getUserId();
    }


}