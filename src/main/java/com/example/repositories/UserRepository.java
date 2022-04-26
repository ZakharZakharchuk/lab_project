package com.example.repositories;

import com.example.dto.user.UserDTO;
import com.example.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByName(String username);
}
