package com.dionisius.finalproject.logservice.controller;

import com.dionisius.finalproject.logservice.model.Log;
import com.dionisius.finalproject.logservice.payload.BaseResponse;
import com.dionisius.finalproject.logservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping()
    public ResponseEntity<Log> create(@RequestBody Log log){
        Log logCreated = logService.create(log);
        return ResponseEntity.ok(logCreated);
    }
    @GetMapping
    public ResponseEntity<BaseResponse<List<Log>>> getList(){
        try {
            List<Log> logs = logService.listLog();
            return ResponseEntity.ok(new BaseResponse<>(logs));
        }catch (Exception e){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
