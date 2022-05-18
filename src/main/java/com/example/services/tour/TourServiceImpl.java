package com.example.services.tour;

import com.example.dto.tour.TourDTO;
import com.example.models.Bucket;
import com.example.models.Tour;
import com.example.models.User;
import com.example.repositories.TourRepository;
import com.example.repositories.UserRepository;
import com.example.services.bucket.BucketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final BucketService bucketService;

    public TourServiceImpl(TourRepository tourRepository, UserRepository userRepository, BucketService bucketService) {
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
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
    /*    @Transactional*/
    public void addToUserBucket(Integer tourId, String username) {

        // TODO: move this method to BucketService

        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new RuntimeException("User not found" + username);
        }

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            bucket = bucketService.createBucket(user);
        }
        bucketService.addTour(bucket, tourId);
    }

    @Override
    public void deleteFromUserBucket(Integer tourId, String username) {
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new RuntimeException("User not found" + username);
        }
        Bucket bucket = user.getBucket();
//        tourRepository.removeTourById(tourId, bucket.getId());
    }

    public TourDTO mapTour(Tour tour) {
        TourDTO tourDTO = new TourDTO();
        String name = tour.getName();
        int pricePerPerson = tour.getPricePerPerson();
        tourDTO.setName(name);
        tourDTO.setPricePerPerson(pricePerPerson);
        return tourDTO;
    }
}
