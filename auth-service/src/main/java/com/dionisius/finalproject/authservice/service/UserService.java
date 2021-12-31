package com.dionisius.finalproject.authservice.service;

import com.dionisius.finalproject.authservice.payload.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserInfo getUserInfo(String username) throws UsernameNotFoundException;
}
