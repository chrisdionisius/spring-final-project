package com.dionisius.finalproject.categoryservice.impl.controller;

import com.dionisius.finalproject.categoryservice.api.dto.BaseResponse;
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
    public ResponseEntity<BaseResponse<CategoryOutput>> getOne(@PathVariable Integer id) {
        try {
            CategoryOutput categoryOutput = categoryService.getOne(id);
            return ResponseEntity.ok(new BaseResponse<>(categoryOutput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "No post found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryOutput>>> getAll(){
        try {
            List<CategoryOutput> categoryOutputs = categoryService.getAll();
            return ResponseEntity.ok(new BaseResponse<>(categoryOutputs));
        }catch (Exception e){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryInput>> addOne(@RequestBody CategoryInput categoryInput){
        if (categoryInput.getName() == null){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            categoryService.addOne(categoryInput);
            return ResponseEntity.ok(new BaseResponse<>(categoryInput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Duplicated")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Duplicated"), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Integer id){
        try {
            categoryService.delete(id);
            return new ResponseEntity(new BaseResponse(Boolean.TRUE,
                    "Success deleting item"), HttpStatus.OK);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "No post found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryInput>> update(@PathVariable Integer id,@RequestBody CategoryInput categoryInput){
        try{
            if (categoryInput.getName() == null){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Bad Request"), HttpStatus.BAD_REQUEST);
            }
            categoryService.update(id, categoryInput);
            return ResponseEntity.ok(new BaseResponse<>(categoryInput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "No post found"), HttpStatus.NOT_FOUND);
            }else if (e.getMessage().equalsIgnoreCase("Duplicated")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Duplicated row"), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
