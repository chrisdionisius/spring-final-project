package com.dionisius.finalproject.categoryservice.api.service;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;

import java.util.List;

public interface CategoryService {
    CategoryOutput getOne(Integer id);
    List<CategoryOutput> getAll();
    void addOne(CategoryInput categoryInput);
}
