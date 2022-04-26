package com.example.repositories;

import com.example.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {

}
