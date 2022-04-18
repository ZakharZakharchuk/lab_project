package com.example.services.user;

import com.example.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
}
