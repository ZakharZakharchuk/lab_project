package com.example.repositories;

import com.example.models.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour,Integer> {
    List<Tour>findAll();
}
