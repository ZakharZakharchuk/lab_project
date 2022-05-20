package com.example.repositories;

import com.example.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByIdIn(List<Integer> tourIds);
}
