package com.dionisius.finalproject.categoryservice.impl.controller;

import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryOutput> getOne(@PathVariable Integer id) {
        try {
            CategoryOutput categoryOutput = categoryService.getOne(id);
            return ResponseEntity.ok(categoryOutput);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryOutput>> getAll(){
        List<CategoryOutput> categoryOutputs = categoryService.getAll();
        return ResponseEntity.ok(categoryOutputs);
    }

    @PostMapping
    public ResponseEntity addOne(@RequestBody CategoryInput categoryInput){
        if (categoryInput.getName() == null){
            return ResponseEntity.noContent().build();
        }
        try {
            categoryService.addOne(categoryInput);
            return ResponseEntity.ok(categoryInput);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Duplicated")){
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody CategoryInput categoryInput){
        try{
            if (categoryInput.getName() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categoryService.update(id, categoryInput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
