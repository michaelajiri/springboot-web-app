package com.infotech.springboot.app.service;

import com.infotech.springboot.app.model.User;
import com.infotech.springboot.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, String username) {
        Optional<User> userFromDb = userRepository.findById(userId);
        if(userFromDb.isPresent()){
            userFromDb.get().setUsername(username);
        }
        User updatedUser = userRepository.save(userFromDb.get());
        return updatedUser;
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
