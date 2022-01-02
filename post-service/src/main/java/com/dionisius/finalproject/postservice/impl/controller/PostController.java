package com.dionisius.finalproject.postservice.impl.controller;

import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    @Qualifier("postServiceImpl")
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostOutput> getOne(@PathVariable Integer id) {
        try {
            PostOutput PostOutput = postService.getOne(id);
            return ResponseEntity.ok(PostOutput);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PostOutput>> getAll(){
        List<PostOutput> PostOutputs = postService.getAll();
        return ResponseEntity.ok(PostOutputs);
    }

    @PostMapping
    public ResponseEntity addOne(@RequestBody PostInput PostInput){
        if (PostInput.getTitle() == null){
            return ResponseEntity.noContent().build();
        }
        try {
            postService.addOne(PostInput);
            return ResponseEntity.ok(PostInput);
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
            postService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody PostInput PostInput){
        try{
            if (PostInput.getTitle() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(postService.update(id, PostInput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
