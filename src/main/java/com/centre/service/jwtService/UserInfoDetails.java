package com.centre.service.jwtService;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.centre.service.model.UserInfo;

public class UserInfoDetails implements UserDetails {
    
    private String name;
    private String password;
    private String status;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UserInfo userInfo) {
        this.name = userInfo.getNom(); // Utilisation de l'instance userInfo
        this.password = userInfo.getMotDePasse();
        this.status = userInfo.getStatus();
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Assurez-vous que authorities est initialisé
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}