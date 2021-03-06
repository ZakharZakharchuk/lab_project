package com.example.services.user;

import com.example.dto.user.UserDTO;
import com.example.models.Role;
import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapDTO).toList();
    }

    @Override
    public void save(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword()))
            throw new RuntimeException("Password is not equals");
        String name = userDTO.getName();
        String email = userDTO.getEmail();
        Role role = Role.USER;
        String password = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(name, email, password, role);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findFirstByName(username);
    }

    public UserDTO mapDTO(User user){
        UserDTO userDTO = new UserDTO();
        String name = user.getName();
        String email = user.getEmail();
        userDTO.setEmail(email);
        userDTO.setName(name);
        return userDTO;
    }
}
