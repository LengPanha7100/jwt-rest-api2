package com.example.demospring.jwtrestapi2.service;

import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.dto.request.CategoryRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory(Integer pageNo , Integer pageSize);

    Category getCategoryById(Long id);

    Category createCategory(CategoryRequest categoryRequest);

    Category updateCategory(Long id, CategoryRequest categoryRequest) ;

    Category deleteCategory(Long id) ;
}
