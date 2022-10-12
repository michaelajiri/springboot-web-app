package com.infotech.springboot.app.controller;

import com.infotech.springboot.app.model.User;
import com.infotech.springboot.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api")
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "user/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_USER')")
    public User addUser(@RequestBody User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        return userService.addUser(user);
    }

    @GetMapping(value = "user/allUsers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public Optional<User> getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping(value = "user/{userId}/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_USER')")
    public User editUser(@PathVariable("userId") Long userId, String username){
        return userService.updateUser(userId, username);
    }

    @DeleteMapping(value = "user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeUser(@PathVariable("userId") Long userId){
        userService.removeUser(userId);
    }
}
