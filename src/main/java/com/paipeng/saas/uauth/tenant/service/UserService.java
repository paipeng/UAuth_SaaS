package com.paipeng.saas.uauth.tenant.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paipeng.saas.uauth.tenant.entity.User;


public interface UserService {

    User save(User user);

    String findLoggedInUsername();

    @Query("select p from User p where p.username = :username and p.tenant = :tenant")
    User findByUsernameAndTenantname(@Param("username") String username,
            @Param("tenant") String tenant);

    List<User> findAllUsers();

    User login(User user) throws Exception;
    User register(User user);
    void logout();
}
