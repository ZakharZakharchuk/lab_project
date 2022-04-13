package com.example.repositories;

import com.example.models.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {
    List<User> findAll();
    void save(User user);
}
