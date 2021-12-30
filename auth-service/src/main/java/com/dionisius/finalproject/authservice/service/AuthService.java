package com.dionisius.finalproject.authservice.service;

import com.dionisius.finalproject.authservice.model.User;
import com.dionisius.finalproject.authservice.payload.TokenResponse;
import com.dionisius.finalproject.authservice.payload.UserRegister;
import com.dionisius.finalproject.authservice.payload.UsernamePassword;

public interface AuthService {
    User register (UserRegister req);
    TokenResponse generateToken(UsernamePassword req);
}
