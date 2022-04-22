package com.example.rest;

import com.example.models.Tour;
import com.example.services.tour.TourService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/tour")
public class RestTourController {
    private final TourService tourService;

    public RestTourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping()
    public List<Tour> showAllTours() {
        return tourService.findAll();
    }
}
