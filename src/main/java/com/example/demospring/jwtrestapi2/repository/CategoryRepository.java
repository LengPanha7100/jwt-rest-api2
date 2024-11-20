package com.example.demospring.jwtrestapi2.repository;

import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.dto.request.CategoryRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Results(id = "categoryId" ,value = {
            @Result(property = "categoryId"  , column = "category_id")
    })
    @Select("""
    SELECT * FROM categories WHERE user_id = #{userId}
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} - 1);
    """)
    List<Category> getAllCategory(Integer userId, Integer pageNo , Integer pageSize);

    @Select("""
    SELECT * FROM categories WHERE category_id = #{id} AND user_id = #{userId};
    """)
    @ResultMap("categoryId")
    Category getCategoryById(Integer userId, Long id);

    @Select("""
    SELECT * FROM categories WHERE category_id = #{id};
    """)
    @ResultMap("categoryId")
    Category getCategoryByCategoryId(Long id);

    @Select("""
    INSERT INTO categories(name,description,user_id)
    VALUES (#{category.name} ,#{category.description},#{userId})
    returning *
    ;
    """)
    @ResultMap("categoryId")
    Category createCategory(Integer userId,@Param("category") CategoryRequest categoryRequest);

    @Select("""
    UPDATE categories
    SET name = #{category.name} , description = #{category.description} , user_id = #{userId}
    WHERE category_id = #{id}
    returning *;
    """)
    @ResultMap("categoryId")
    Category updateCategory(Integer userId, Long id,@Param("category") CategoryRequest categoryRequest);

    @Select("""
    DELETE FROM categories WHERE category_id = #{id} AND user_id = #{userId};
    """)
    Category deleteCategory( Integer userId,Long id);


}
