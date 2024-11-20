package com.example.demospring.jwtrestapi2.controller;

import com.example.demospring.jwtrestapi2.model.Category;
import com.example.demospring.jwtrestapi2.model.dto.request.CategoryRequest;
import com.example.demospring.jwtrestapi2.model.dto.response.APIResponse;
import com.example.demospring.jwtrestapi2.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Category>>> getAllCategory (@RequestParam (defaultValue = "1") Integer pageNo,
                                                                       @RequestParam (defaultValue = "10") Integer pageSize){
        List<Category> categories = categoryService.getAllCategory(pageNo,pageSize);
        APIResponse<List<Category>> apiResponse = APIResponse.<List<Category>>builder()
                .message("Get all category successfully!")
                .status(HttpStatus.OK)
                .payload(categories)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> getCategoryById (@PathVariable Long id){
        Category categories = categoryService.getCategoryById(id);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .message("Get by id category successfully!")
                .status(HttpStatus.OK)
                .payload(categories)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Category>> createCategory (@RequestBody CategoryRequest categoryRequest){
        Category categories = categoryService.createCategory(categoryRequest);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .message("Created category successfully!")
                .status(HttpStatus.CREATED)
                .payload(categories)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> updateCategory (@PathVariable Long id,@RequestBody CategoryRequest categoryRequest){
        Category categories = categoryService.updateCategory(id,categoryRequest);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .message("Updated category by id successfully!")
                .status(HttpStatus.OK)
                .payload(categories)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> deleteCategory (@PathVariable Long id){
        Category category =categoryService.deleteCategory(id);
        APIResponse<Category> apiResponse = APIResponse.<Category>builder()
                .message("Deleted category by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
