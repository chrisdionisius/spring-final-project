package com.dionisius.finalproject.categoryservice.impl.controller;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryOutput> getOne(@PathVariable Integer id) {
        CategoryOutput categoryOutput = categoryService.getOne(id);
        return ResponseEntity.ok(categoryOutput);
    }

    @GetMapping
    public ResponseEntity<List<CategoryOutput>> getAll(){
        List<CategoryOutput> categoryOutputs = categoryService.getAll();
        return ResponseEntity.ok(categoryOutputs);
    }

    @PostMapping
    public ResponseEntity addOne(@RequestBody CategoryInput categoryInput){
        categoryService.addOne(categoryInput);
        return ResponseEntity.ok(null);
    }
}
