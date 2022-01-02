package com.dionisius.finalproject.authservice.controller;

import com.dionisius.finalproject.authservice.payload.TokenResponse;
import com.dionisius.finalproject.authservice.payload.UserRegister;
import com.dionisius.finalproject.authservice.payload.UsernamePassword;
import com.dionisius.finalproject.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegister userRegister){
        authService.register(userRegister);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    public ResponseEntity <?> getToken(@RequestBody UsernamePassword usernamePassword){
        TokenResponse token = authService.generateToken(usernamePassword);
        return ResponseEntity.ok(token);
    }
}
