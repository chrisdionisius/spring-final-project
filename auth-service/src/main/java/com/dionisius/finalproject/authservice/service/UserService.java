package com.dionisius.finalproject.authservice.service;

import com.dionisius.finalproject.authservice.payload.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserInfo getUserInfo(String username) throws UsernameNotFoundException;
    List<UserInfo> getAllUserInfo(String username);
}
