package com.dionisius.finalproject.authservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String photo;
    @Column(columnDefinition = "boolean default false")
    private boolean is_registered=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.is_registered;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.is_registered;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.is_registered;
    }

    @Override
    public boolean isEnabled() {
        return this.is_registered;
    }
}
