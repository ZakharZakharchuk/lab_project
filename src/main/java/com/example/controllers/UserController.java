package com.example.controllers;

import com.example.dto.user.UserDTO;
import com.example.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/register";
    }

    @PostMapping("/new")
    public String savaUser(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }
}
