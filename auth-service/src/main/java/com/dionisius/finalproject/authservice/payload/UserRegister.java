package com.dionisius.finalproject.authservice.payload;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegister {
    public String username;
    public String password;
    public String email;
    public String photo;
}
