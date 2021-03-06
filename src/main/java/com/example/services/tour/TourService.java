package com.example.services.tour;

import com.example.dto.tour.TourDTO;
import com.example.models.Tour;

import java.util.List;

public interface TourService {
    List<Tour> findAll();

    void save(TourDTO tourDTO);

    void addToUserBucket(Integer tourId, String username);

//    void deleteTour(int id);

    void deleteFromUserBucket(Integer tourId, String username);
}
