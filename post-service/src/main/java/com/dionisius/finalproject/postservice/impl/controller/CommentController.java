package com.dionisius.finalproject.postservice.impl.controller;

import com.dionisius.finalproject.postservice.api.dto.CommentInput;
import com.dionisius.finalproject.postservice.api.dto.CommentOutput;
import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    @Qualifier("commentServiceImpl")
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentOutput> getOneComment(@PathVariable Integer id) {
        try {
            CommentOutput commentOutput = commentService.getOneComment(id);
            return ResponseEntity.ok(commentOutput);
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentOutput>> getAllComment(){
        List<CommentOutput> commentOutputs = commentService.getAllComment();
        return ResponseEntity.ok(commentOutputs);
    }

    @PostMapping
    public ResponseEntity addOne(@RequestBody CommentInput commentInput){
        if (commentInput.getContent() == null){
            return ResponseEntity.noContent().build();
        }
        try {
            commentService.addOneComment(commentInput);
            return ResponseEntity.ok(commentInput);
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
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody CommentInput commentInput){
        try{
            if (commentInput.getContent() == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(commentService.updateComment(id, commentInput));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Not Found")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
