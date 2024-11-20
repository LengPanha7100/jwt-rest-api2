package com.example.demospring.jwtrestapi2.service.serviceImp;

import com.example.demospring.jwtrestapi2.exception.NotFoundException;
import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.Expenses;
import com.example.demospring.jwtrestapi2.model.dto.request.ExpensesRequest;
import com.example.demospring.jwtrestapi2.model.dto.response.APIResponse;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnumOrderBy;
import com.example.demospring.jwtrestapi2.model.enums.ExpensesEnums;
import com.example.demospring.jwtrestapi2.repository.ExpensesRepository;
import com.example.demospring.jwtrestapi2.service.AppUserService;
import com.example.demospring.jwtrestapi2.service.CategoryService;
import com.example.demospring.jwtrestapi2.service.ExpensesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesServiceImp implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final AppUserService appUserService;
    private final CategoryService categoryService;

    public ExpensesServiceImp(ExpensesRepository expensesRepository, AppUserService appUserService, CategoryService categoryService) {
        this.expensesRepository = expensesRepository;
        this.appUserService = appUserService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Expenses> getAllExpenses(Integer pageNo, Integer pageSize, ExpensesEnums expensesEnums, ExpensesEnumOrderBy expensesEnumOrderBy) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        return expensesRepository.getAllExpenses(pageNo,pageSize,expensesEnums.name(),expensesEnumOrderBy.name(),userId);
    }

    @Override
    public Expenses createExpense(ExpensesRequest expensesRequest) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        categoryService.getCategoryById(expensesRequest.getCategoryId());
        return expensesRepository.createExpense(expensesRequest, userId);
    }

    @Override
    public Expenses getExpenseById(Long id) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        Expenses expenses = expensesRepository.getExpenseById(id,userId);
        if(expenses == null) {
            throw new NotFoundException("Get expense by id "+ id + " not found");
        }
        return expenses ;
    }

    @Override
    public Expenses updateExpenseById(Long id, ExpensesRequest expensesRequest) {
        getExpenseById(id);
        Integer userId = appUserService.getUsernameOfCurrentUser();
        categoryService.getCategoryById(expensesRequest.getCategoryId());
        return expensesRepository.updateExpenseById(id,expensesRequest,userId);
    }

    @Override
    public void deleteExpense(Long id) {
        getExpenseById(id);
        Integer userId = appUserService.getUsernameOfCurrentUser();
        expensesRepository.deleteExpense(id,userId);
    }
}
