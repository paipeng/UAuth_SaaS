package com.paipeng.saas.uauth.tenant.service;

import com.paipeng.saas.uauth.config.ApplicationConfig;
import com.paipeng.saas.uauth.security.AppAuthenticationToken;
import com.paipeng.saas.uauth.tenant.entity.User;
import com.paipeng.saas.uauth.tenant.repository.UserRepository;
import com.paipeng.saas.uauth.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User justSavedUser = userRepository.save(user);
        logger.info("User:" + justSavedUser.getUsername() + " saved.");
        return justSavedUser;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails) userDetails).getUsername();
            logger.info("Logged in username:" + username);
            return username;
        }
        return null;
    }

    @Override
    public User findByUsernameAndTenantname(String username, String tenant) {
        logger.info("findByUsernameAndTenantname: " + username + "tenant: " + tenant);
        User user = userRepository.findByUsernameAndTenantname(username,
                tenant);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format(
                            "Username not found for domain, "
                                    + "username=%s, tenant=%s",
                            username, tenant));
        }
        logger.info("Found user with username:" + user.getUsername()
                + " from tenant:" + user.getTenant());
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(User user) throws Exception {
        logger.info("login: " + user.getUsername());
        logger.info("password: " + user.getPassword());
        logger.info("tenant: " + user.getTenant());

        if (user.getPassword() != null) {
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //String encodedPassword = passwordEncoder.encode(user.getPassword());
            //logger.trace("encodedPassword: " + encodedPassword);
            User foundUser = userRepository.findByUsernameAndTenantname(user.getUsername(), user.getTenant());

            if (foundUser != null) {
                logger.info("user found: " + foundUser.getId());
                if (CommonUtil.validatePassword(user.getPassword(), foundUser.getPassword())) {
                    String token = CommonUtil.generateJWTToken(applicationConfig.getSecurityJwtSecret(), foundUser);
                    logger.info("token: " + token);
                    foundUser.setToken(token);
                    logger.info("update user");
                    foundUser = userRepository.saveAndFlush(foundUser);
                    return foundUser;
                } else {
                    throw new Exception("401");
                }
            } else {
                throw new Exception("401");
            }
        }
        throw new Exception("401");
    }

    @Override
    public void logout() {
        AppAuthenticationToken auth = (AppAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                logger.info("auth: " + grantedAuthority.getAuthority());
                logger.info("auth: " + grantedAuthority);
            }
            User user = (User) auth.getDetails();
            if (user != null) {
                logger.info("loginUser: " + user.getUsername());
                user.setToken(null);
                userRepository.saveAndFlush(user);
            }
        }
    }

    @Override
    public User register(User user) {
        return user;
    }

}
