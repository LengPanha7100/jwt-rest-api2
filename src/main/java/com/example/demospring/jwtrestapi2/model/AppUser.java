package com.example.demospring.jwtrestapi2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
//userDetail default login with username and password So it call it want to custom (login that you want for example email or fullName that field you want  )
//custom user for login
public class AppUser implements UserDetails {
    private Integer userId;
    private String fullName;
    private String email;
    private String password;

    //role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //when user login with username or anything
    @Override
    public String getUsername() {
        return email;
    }
    //account expired
    //true account non-expired
    //false account expired; default
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //account lock
    //true account non-lock
    //false account lock; default
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //password non-expired
    //true password non-expired
    //false password expired default
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //have account but disable if false when true account enable
    @Override
    public boolean isEnabled() {
        return true;
    }


}
