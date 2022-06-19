package com.paipeng.saas.uauth.controller;

import com.paipeng.saas.uauth.tenant.entity.User;
import com.paipeng.saas.uauth.tenant.service.UserService;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final static Logger logger = LogManager.getLogger(UserController.class.getSimpleName());
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public User login(@NotNull @RequestBody User user) throws Exception {
        logger.trace("login" + user);
        return userService.login(user);
    }
}
