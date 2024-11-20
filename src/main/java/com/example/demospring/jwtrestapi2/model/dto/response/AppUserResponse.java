package com.example.demospring.jwtrestapi2.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Integer userId;
    private String fullName;
    private String email;
}
