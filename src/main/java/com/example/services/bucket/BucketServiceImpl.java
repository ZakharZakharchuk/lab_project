package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.dto.bucket.TourDetailsDTO;
import com.example.models.Bucket;
import com.example.models.Tour;
import com.example.models.User;
import com.example.repositories.BucketRepository;
import com.example.repositories.TourRepository;
import com.example.services.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final TourRepository tourRepository;
    private final UserService userService;

    public BucketServiceImpl(BucketRepository bucketRepository, TourRepository tourRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.tourRepository = tourRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Integer> tourIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Tour> tours = getCollectionByTourIds(tourIds);
        bucket.setTour(tours);
        return bucket;
    }

    private List<Tour> getCollectionByTourIds(List<Integer> tourIds) {
        return tourIds.stream()
                .map(tourRepository::getOne)
                .toList();
    }

    @Override
    public void addTour(Bucket bucket, List<Integer> tourIds) {
        List<Tour>tours = bucket.getTour();
        List<Tour> newTourList = tours==null?new ArrayList<>(): new ArrayList<>(tours);
        newTourList.addAll(getCollectionByTourIds(tourIds));
        bucket.setTour(newTourList);
        bucketRepository.save(bucket);
    }

    @Override
    public Bucket getBucketByUser(String name) {
        User user = userService.findByName(name);
        if(user==null||user.getBucket()==null)
            return new Bucket();
        return user.getBucket();
    }
}
