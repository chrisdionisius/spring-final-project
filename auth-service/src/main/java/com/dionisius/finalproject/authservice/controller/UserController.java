package com.dionisius.finalproject.authservice.controller;

import com.dionisius.finalproject.authservice.payload.BaseResponse;
import com.dionisius.finalproject.authservice.payload.TokenResponse;
import com.dionisius.finalproject.authservice.payload.UserInfo;
import com.dionisius.finalproject.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<BaseResponse<?>> getUserInfo(Principal principal){
        if (principal.getName() == null){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            UserInfo userInfo = userService.getUserInfo(principal.getName());
            return ResponseEntity.ok(new BaseResponse<>(userInfo));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Bad Credential")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Bad Credential"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
