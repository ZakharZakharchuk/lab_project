package com.example.controllers;

import com.example.dto.tour.TourDTO;
import com.example.services.tour.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping()
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

    @GetMapping("/tours/new")
    public String newTour(Model model) {
        model.addAttribute("tour", new TourDTO());
        return "tour/new";
    }

    @PostMapping("/tours/new")
    public String saveTour(TourDTO tourDTO) {
        tourService.save(tourDTO);
        return "redirect:/";
    }


    @GetMapping("/tours/{id}/bucket")
    public String addToBucket(@PathVariable int id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        // TO DO не можна ідентифікувати по name
        tourService.addToUserBucket(id, principal.getName());
        return "redirect:/";
    }
    //TODO Зробити POST
    @DeleteMapping("/tours/{id}/delete")
    public String deleteTour(@PathVariable int id){
        tourService.deleteTour(id);
        return "redirect:/";
    }
}
