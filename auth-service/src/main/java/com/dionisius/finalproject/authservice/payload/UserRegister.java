package com.dionisius.finalproject.authservice.payload;

import lombok.Data;

@Data
public class UserRegister {
    public String username;
    public String password;
    public String email;
    public String photo;
}
