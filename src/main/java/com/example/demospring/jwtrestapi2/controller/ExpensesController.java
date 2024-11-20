package com.example.demospring.jwtrestapi2.controller;

import com.example.demospring.jwtrestapi2.model.Expenses;
import com.example.demospring.jwtrestapi2.model.dto.request.ExpensesRequest;
import com.example.demospring.jwtrestapi2.model.dto.response.APIResponse;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnumOrderBy;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnums;
import com.example.demospring.jwtrestapi2.repository.ExpensesRepository;
import com.example.demospring.jwtrestapi2.service.ExpensesService;
import com.example.demospring.jwtrestapi2.service.serviceImp.ExpensesServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
@SecurityRequirement(name = "bearerAuth")
public class ExpensesController {
    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Expenses>>> getAllExpenses (@RequestParam(defaultValue = "1") Integer pageNo,
                                                                       @RequestParam (defaultValue = "10") Integer pageSize,
                                                                       @RequestParam ExpensesEnums expensesEnums,
                                                                       @RequestParam ExpensesEnumOrderBy expensesEnumOrderBy){
        List<Expenses> expenses = expensesService.getAllExpenses(pageNo,pageSize,expensesEnums,expensesEnumOrderBy);
        APIResponse<List<Expenses>> apiResponse = APIResponse.<List<Expenses>>builder()
                .message("Get all expenses successfully!")
                .status(HttpStatus.OK)
                .payload(expenses)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Expenses>> getExpenseById (@PathVariable Long id){
        Expenses expenses = expensesService.getExpenseById(id);
        APIResponse<Expenses> apiResponse = APIResponse.<Expenses>builder()
                .message("Get expense by is successfully!")
                .status(HttpStatus.OK)
                .payload(expenses)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Expenses>> createExpense (@RequestBody ExpensesRequest expensesRequest){
        Expenses expenses = expensesService.createExpense(expensesRequest);
        APIResponse<Expenses> apiResponse = APIResponse.<Expenses>builder()
                .message("Get expense by is successfully!")
                .status(HttpStatus.CREATED)
                .payload(expenses)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Expenses>> updateExpenseById (@PathVariable Long id , @RequestBody ExpensesRequest expensesRequest){
        Expenses expenses = expensesService.updateExpenseById(id, expensesRequest);
        APIResponse<Expenses> apiResponse = APIResponse.<Expenses>builder()
                .message("Get expense by is successfully!")
                .status(HttpStatus.OK)
                .payload(expenses)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Expenses>> deleteExpense (@PathVariable Long id){
       expensesService.deleteExpense(id);
        APIResponse<Expenses> apiResponse = APIResponse.<Expenses>builder()
                .message("Get expense by is successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


}
