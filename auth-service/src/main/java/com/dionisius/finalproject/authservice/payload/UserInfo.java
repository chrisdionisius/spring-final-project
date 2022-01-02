package com.dionisius.finalproject.authservice.payload;

import lombok.Data;

@Data
public class UserInfo {
    public Long id;
    public String username;
    public String email;
    public String photo;
    public String role;
    private boolean is_registered;
}
