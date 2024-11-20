package com.example.demospring.jwtrestapi2.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private String fullName;
    private String email;
    private String password;
}
