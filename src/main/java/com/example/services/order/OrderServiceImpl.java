package com.example.services.order;

import com.example.models.Order;
import com.example.models.User;
import com.example.repositories.OrderRepository;
import com.example.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }


}
