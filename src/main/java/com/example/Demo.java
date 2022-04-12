package com.example;

import com.example.dao.PurchaseDAO;
import com.example.dao.TourDAO;
import com.example.dao.UserDAO;
import com.example.models.Purchase;
import com.example.models.Tour;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.sql.SQLException;
import java.util.List;

public class Demo {
    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
            Demo demo = new Demo();
            System.out.println(demo.userRepository.findAll());
    }
}
