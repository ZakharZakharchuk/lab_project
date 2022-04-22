package com.example.repositories;

import com.example.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {
    List<User> findAll();
    void save(User user);
    User findFirstByName(String name);
}
