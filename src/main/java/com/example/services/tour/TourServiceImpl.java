package com.example.services.tour;

import com.example.dto.tour.TourDTO;
import com.example.models.Tour;
import com.example.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public void save(TourDTO tourDTO) {
        Tour tour = new Tour(tourDTO.getName(), tourDTO.getPricePerPerson());
        tourRepository.save(tour);
    }
}
