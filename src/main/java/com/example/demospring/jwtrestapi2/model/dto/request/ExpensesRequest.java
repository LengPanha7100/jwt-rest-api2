package com.example.demospring.jwtrestapi2.model.dto.request;

import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.dto.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesRequest {
    private Double amount;
    private String description;
    private LocalDateTime date;
    private  Long categoryId;
}
