package com.example.controllers;

import com.example.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    @Autowired
    TourRepository tourRepository;

    @GetMapping("/show")
    public String showAllTours(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        return "showTours";
    }
}
