package com.paipeng.saas.uauth.tenant.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails
        extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private String tenant;

    public CustomUserDetails(String username, String password,
            Collection<? extends GrantedAuthority> authorities, String tenant) {
        super(username, password, authorities);
        this.tenant = tenant;
    }

    // Getters and Setters
    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

}
