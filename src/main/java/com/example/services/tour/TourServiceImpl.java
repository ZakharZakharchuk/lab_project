package com.example.services.tour;

import com.example.dto.tour.TourDTO;
import com.example.models.Bucket;
import com.example.models.Tour;
import com.example.models.User;
import com.example.repositories.TourRepository;
import com.example.services.bucket.BucketService;
import com.example.services.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final UserService userService;
    private final BucketService bucketService;

    public TourServiceImpl(TourRepository tourRepository, UserService userService, BucketService bucketService) {
        this.tourRepository = tourRepository;
        this.userService = userService;
        this.bucketService = bucketService;
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

    @Override
    @Transactional
    public void addToUserBucket(Integer tourId, String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("User not found"+ username);
        }
        Bucket bucket = user.getBucket();
        if(bucket==null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(tourId));
            user.setBucket(newBucket);
            userService.save(user);
        }
        else{
            bucketService.addTour(bucket, Collections.singletonList(tourId));
        }
    }


    public TourDTO mapTour(Tour tour){
        TourDTO tourDTO = new TourDTO();
        String name = tour.getName();
        int pricePerPerson = tour.getPricePerPerson();
        tourDTO.setName(name);
        tourDTO.setPricePerPerson(pricePerPerson);
        return tourDTO;
    }
}
