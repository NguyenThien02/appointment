package com.do_an.appointment.services;

import com.do_an.appointment.dtos.CategoryDTO;
import com.do_an.appointment.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    boolean exsitingById(Long id);
}