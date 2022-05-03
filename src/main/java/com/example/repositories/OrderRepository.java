package com.example.repositories;

import com.example.models.Order;
import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByUser(User user);
}
