package com.example.demospring.jwtrestapi2.model;

import com.example.demospring.jwtrestapi2.model.dto.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenses {
    private Long expenseId;
    private Double amount;
    private LocalDateTime date;
    private AppUserResponse user;
    private Category category;

}
