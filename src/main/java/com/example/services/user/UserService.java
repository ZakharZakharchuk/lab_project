package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(UserDTO userDTO);
}
