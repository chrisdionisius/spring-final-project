package com.dionisius.finalproject.authservice.service.impl;

import com.dionisius.finalproject.authservice.model.User;
import com.dionisius.finalproject.authservice.repository.UserRepository;
import com.dionisius.finalproject.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getDistinctTopByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Username Not Found");

        return user;
    }
}
