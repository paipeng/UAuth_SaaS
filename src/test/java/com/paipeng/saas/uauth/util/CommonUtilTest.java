package com.paipeng.saas.uauth.util;

import com.paipeng.saas.uauth.tenant.entity.Role;
import com.paipeng.saas.uauth.tenant.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CommonUtilTest {
    private static final String JWT_SECRET = "5161127a80ff47a1855176c345a1de39833b486ea3dd40629081ab0370a1632c87496492fb634c60a458182c69a7f0d0";

    @org.junit.jupiter.api.Test
    void generateJWTToken() {
        User user  = new User();
        user.setUsername("paipeng");
        user.setTenant("tenant_1");
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setRole("ADMIN");
        role.setId(1);
        roles.add(role);
        user.setRoles(roles);
        String token = CommonUtil.generateJWTToken(JWT_SECRET, user);
        System.out.println("token: " + token);
    }

    @org.junit.jupiter.api.Test
    void generateJWTToken2() {
        User user  = new User();
        user.setUsername("pai");
        user.setTenant("tenant_2");
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("ADMIN");
        role.setId(1);
        roles.add(role);
        user.setRoles(roles);

        String token = CommonUtil.generateJWTToken(JWT_SECRET, user);
        System.out.println("token: " + token);
    }

    @Test
    void validatePassword() {
        String password1 = "test1234";
        String password2 = "$2a$10$X6QDiTMvTUZ09dNN5STWSOkBe8qzkan1sPxLe60H3x1jaH4yLfu06";
        boolean validate = CommonUtil.validatePassword(password1, password2);
        System.out.println("validate: " + validate);
    }
}