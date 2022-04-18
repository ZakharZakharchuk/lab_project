package com.example.controllers;

import com.example.models.Tour;
import com.example.repositories.TourRepository;
import com.example.services.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping()
    public String showAllTours(Model model) {
        model.addAttribute("tours", tourService.findAll());
        return "tour/show";
    }

    @GetMapping("/new")
    public String newTour(Model model) {
        model.addAttribute("tour", new Tour());
        return "tour/new";
    }

    @PostMapping()
    public String saveTour(Tour tour) {
        tourService.save(tour);
        return "redirect:/tours";
    }
}
