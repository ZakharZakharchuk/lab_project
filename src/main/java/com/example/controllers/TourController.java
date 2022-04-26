package com.example.controllers;

import com.example.dto.tour.TourDTO;
import com.example.models.Tour;
import com.example.repositories.TourRepository;
import com.example.services.tour.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        model.addAttribute("tour", new TourDTO());
        return "tour/new";
    }

    @PostMapping()
    public String saveTour(TourDTO tourDTO) {
        tourService.save(tourDTO);
        return "redirect:/tours";
    }

    @GetMapping("/{id}/bucket")
    public String addToBucket(@PathVariable int id, Principal principal) {
        /*if (principal == null) {
            return "redirect:/tours";
        }*/
        tourService.addToUserBucket(id, principal.getName());
        return "redirect:/tours";
    }
}
