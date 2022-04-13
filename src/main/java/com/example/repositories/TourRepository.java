package com.example.repositories;

import com.example.models.Tour;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TourRepository extends Repository<Tour, Integer> {
    List<Tour>findAll();
    void save(Tour tour);
}
