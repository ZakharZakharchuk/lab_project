package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail());
        userRepository.save(user);
    }
}
