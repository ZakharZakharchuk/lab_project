package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{
    List<UserDTO> findAll();

    void save(User user);

    void save(UserDTO userDTO);

    User findByName(String username);
}
