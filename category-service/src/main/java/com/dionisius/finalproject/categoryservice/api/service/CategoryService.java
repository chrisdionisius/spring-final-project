package com.dionisius.finalproject.categoryservice.api.service;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.data.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryOutput getOne(Integer id);
    List<CategoryOutput> getAll();
    void addOne(CategoryInput categoryInput);
    void delete(Integer id);
    Category update(Integer id, CategoryInput categoryInput);
}
