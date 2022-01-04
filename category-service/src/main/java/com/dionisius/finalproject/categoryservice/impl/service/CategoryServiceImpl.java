package com.dionisius.finalproject.categoryservice.impl.service;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.api.dto.output.PostOutput;
import com.dionisius.finalproject.categoryservice.api.service.CategoryService;
import com.dionisius.finalproject.categoryservice.data.model.Category;
import com.dionisius.finalproject.categoryservice.impl.repository.CategoryRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            throw new RuntimeException("Not Found");
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
        try {
            categoryRepository.save(category);
        }catch (Exception e){
            throw new RuntimeException("Duplicated");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Integer id, CategoryInput categoryInput) {
        Optional<Category> categoryUpdated = categoryRepository.findById(id);
        if (categoryUpdated.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        try {
            categoryUpdated.get().setName(categoryInput.getName());
            return  categoryRepository.save(categoryUpdated.get());
        }catch (Exception e){
            throw new RuntimeException("Duplicated");
        }
    }
    private PostOutput checkPost(Integer id){
        String result = null;
        try{
            final String uri = "http://192.168.56.1:7200/post/postByCategory/"+id;
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.getForObject(uri, String.class);
            Gson gsonPosts= new Gson();
            PostOutput postOutput = gsonPosts.fromJson(result,PostOutput.class);
            return postOutput;
        }catch (Exception e){
            return null;
        }
    }
}
