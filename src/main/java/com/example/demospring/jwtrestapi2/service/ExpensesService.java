package com.example.demospring.jwtrestapi2.service;

import com.example.demospring.jwtrestapi2.model.Expenses;
import com.example.demospring.jwtrestapi2.model.dto.request.ExpensesRequest;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnumOrderBy;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnums;

import java.util.List;

public interface ExpensesService {
    List<Expenses> getAllExpenses(Integer pageNo, Integer pageSize, ExpensesEnums expensesEnums, ExpensesEnumOrderBy expensesEnumOrderBy);


    Expenses createExpense(ExpensesRequest expensesRequest);

    Expenses getExpenseById(Long id);

    Expenses updateExpenseById(Long id, ExpensesRequest expensesRequest);

    void deleteExpense(Long id);
}
