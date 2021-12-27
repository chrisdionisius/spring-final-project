package com.dionisius.finalproject.categoryservice.impl.service;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.api.service.CategoryService;
import com.dionisius.finalproject.categoryservice.data.model.Category;
import com.dionisius.finalproject.categoryservice.impl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryOutput getOne(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            return null;
        }
        return CategoryOutput.builder()
                .id(category.get().getId())
                .name(category.get().getName())
                .build();
    }

    @Override
    public List<CategoryOutput> getAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<CategoryOutput> categoryOutputs = new ArrayList<>();
        for (Category category : categories){
            CategoryOutput categoryOutput = CategoryOutput.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            categoryOutputs.add(categoryOutput);
        }
        return categoryOutputs;
    }

    @Override
    public void addOne(CategoryInput categoryInput) {
        Category category = Category.builder()
                .name(categoryInput.getName())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryOutput update(Integer id, CategoryInput categoryInput) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            return null;
        }
        return null;
    }


}
