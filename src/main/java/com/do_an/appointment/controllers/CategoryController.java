package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.CategoryDTO;
import com.do_an.appointment.models.Category;
import com.do_an.appointment.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //Thêm category
    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ){
        try {
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Category category = categoryService.createCategory(categoryDTO);
            return ResponseEntity.ok(category);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Lấy ra category có id = ?
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    //Hiện tất cả các Categories
    @GetMapping("")//http://localhost:8080/api/appointment/categories?page=1&limit=10
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    //Cập nhật category với id = ?
    @PutMapping("{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable("id") long id,
                                                @RequestBody CategoryDTO categoryDTO
    ){
        Category category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(category);
    }
    //Xóa Category với id = ?
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Successfully deleted category with id = " + id);
    }

}
