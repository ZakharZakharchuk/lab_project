package com.example.controllers;

import com.example.dao.TourDAO;
import com.example.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final UserService userService;

    @Autowired
    public TourController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public String showAllTours(Model model) {
        model.addAttribute("tours", userService.findAll());
        return "showTours";
    }
}
