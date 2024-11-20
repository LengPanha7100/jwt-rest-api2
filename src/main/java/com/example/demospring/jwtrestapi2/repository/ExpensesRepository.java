package com.example.demospring.jwtrestapi2.repository;

import com.example.demospring.jwtrestapi2.model.Expenses;
import com.example.demospring.jwtrestapi2.model.dto.request.ExpensesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpensesRepository {
    @Results(id = "expenseId" ,value = {
            @Result(property = "expenseId" , column = "expense_id"),
            @Result(property = "user" , column = "user_id",
            one = @One(select = "com.example.demospring.jwtrestapi2.repository.AppUserRepository.getUserById")
            ),
            @Result(property = "category",column = "category_id",
            one = @One(select = "com.example.demospring.jwtrestapi2.repository.CategoryRepository.getCategoryByCategoryId")
            )
    })
    @Select(""" 
    SELECT * FROM expenses
    WHERE user_id = #{userId}
    ORDER BY ${sortBy} ${orderBy}
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} -1);
    """)
    List<Expenses> getAllExpenses(Integer pageNo, Integer pageSize, String sortBy, String orderBy, Integer userId);

    @Select("""
    SELECT * FROM expenses WHERE expense_id = #{id} AND user_id = #{userId} ;
    """)
    @ResultMap("expenseId")
    Expenses getExpenseById(Long id, Integer userId);

    @Select("""
    INSERT INTO expenses(amount, description, date, user_id, category_id)
    VALUES (#{expense.amount},#{expense.description},#{expense.date},#{userId},#{expense.categoryId})
    returning *;
    """)
    @ResultMap("expenseId")
    Expenses createExpense(@Param("expense") ExpensesRequest expensesRequest, Integer userId);

    @Select("""
    UPDATE expenses
    SET amount = #{expense.amount} , description = #{expense.description} , date = #{expense.date} , category_id = #{expense.categoryId}
    WHERE expense_id = #{id}  AND user_id = #{userId}
    returning *;
    """)
    Expenses updateExpenseById(Long id, @Param("expense") ExpensesRequest expensesRequest, Integer userId);

    @Delete("""
    DELETE FROM expenses WHERE  expense_id = #{id} AND #{userId};
    """)
    void deleteExpense(Long id, Integer userId);
}
