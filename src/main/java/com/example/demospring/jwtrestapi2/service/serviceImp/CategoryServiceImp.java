package com.example.demospring.jwtrestapi2.service.serviceImp;

import com.example.demospring.jwtrestapi2.exception.BadRequestException;
import com.example.demospring.jwtrestapi2.exception.NotFoundException;
import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.dto.request.CategoryRequest;
import com.example.demospring.jwtrestapi2.repository.CategoryRepository;
import com.example.demospring.jwtrestapi2.service.AppUserService;
import com.example.demospring.jwtrestapi2.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private AppUserService appUserService;

    @Override
    public List<Category> getAllCategory(Integer pageNo , Integer pageSize) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        List<Category> category = categoryRepository.getAllCategory(userId,pageNo,pageSize);
        return category;
    }

    @Override
    public Category getCategoryById(Long id) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        Category category = categoryRepository.getCategoryById(userId,id);
        if(category == null){
            throw new NotFoundException("Get category by id "+ id + "not found");
        }

        return category;

    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        Category category = categoryRepository.createCategory(userId,categoryRequest);
        return category;
    }

    @Override
    public Category updateCategory(Long id, CategoryRequest categoryRequest) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        Category category = categoryRepository.updateCategory(userId,id,categoryRequest);
        if(category == null){
            throw new NotFoundException("Updated category by id "+ id + " not found");
        }
        return category;
    }

    @Override
    public Category deleteCategory(Long id) {
        Integer userId = appUserService.getUsernameOfCurrentUser();
        getCategoryById(id);
        Category category = categoryRepository.deleteCategory(userId,id);
        return category;
    }
}
