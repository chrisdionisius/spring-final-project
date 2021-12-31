package com.dionisius.finalproject.authservice.controller;

import com.dionisius.finalproject.authservice.payload.UserInfo;
import com.dionisius.finalproject.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;
    @GetMapping
    public ResponseEntity<?> getHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Principal principal){
        UserInfo userInfo = userService.getUserInfo(principal.getName());
        return ResponseEntity.ok(userInfo);
    }
}
