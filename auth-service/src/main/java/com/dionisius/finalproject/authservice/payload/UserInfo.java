package com.dionisius.finalproject.authservice.payload;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserInfo {
    public String username;
    public String email;
    public String photo;
    public String role;
    private boolean is_registered;
}
