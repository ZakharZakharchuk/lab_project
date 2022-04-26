package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();

    void save(UserDTO userDTO);

    void save(User user);

    User findByName(String username);
}
