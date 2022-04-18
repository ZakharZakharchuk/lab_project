package com.example.services.tour;

import com.example.models.Tour;

import java.util.List;

public interface TourService {
    List<Tour> findAll();
    void save(Tour tour);
}
