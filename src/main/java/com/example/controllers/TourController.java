package com.example.controllers;

import com.example.models.Tour;
import com.example.repositories.TourRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourRepository tourRepository;

    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping()
    public String showAllTours(Model model) {
        model.addAttribute("tours", tourRepository.findAll());
        return "tour/show";
    }

    @GetMapping("/new")
    public String newTour(Model model) {
        model.addAttribute("tour", new Tour());
        return "tour/new";
    }

    @PostMapping()
    public String saveTour(Tour tour) {
        tourRepository.save(tour);
        return "redirect:/tours";
    }
}
