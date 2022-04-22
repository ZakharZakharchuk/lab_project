package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    void save(UserDTO userDTO);
}
