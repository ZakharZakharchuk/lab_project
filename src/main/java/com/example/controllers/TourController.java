package com.example.controllers;

import com.example.repositories.TourRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourRepository tourRepository;

    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping("/show")
    public String showAllTours(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        return "showAllTours";
    }
}
