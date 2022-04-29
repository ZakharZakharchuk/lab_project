package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.dto.bucket.BucketDetailsDTO;
import com.example.models.Bucket;
import com.example.models.Tour;
import com.example.models.User;
import com.example.repositories.BucketRepository;
import com.example.repositories.TourRepository;
import com.example.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Bucket createBucket(User user, List<Integer> tourIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Tour> tours = getCollectionByTourIds(tourIds);
        bucket.setTour(tours);
        return bucket;
    }

    private List<Tour> getCollectionByTourIds(List<Integer> tourIds) {
        return tourIds.stream()
                .map(x -> tourRepository.findById(x).orElse(null))
                .toList();
    }

    @Override
    public void addTour(Bucket bucket, List<Integer> tourIds) {
        List<Tour> tours = bucket.getTour();
        List<Tour> newTourList = tours == null ? new ArrayList<>() : new ArrayList<>(tours);
        newTourList.addAll(getCollectionByTourIds(tourIds));
        bucket.setTour(newTourList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null)
            return new BucketDTO();

        BucketDTO bucketDTO = new BucketDTO();
        Map<Integer, BucketDetailsDTO> mapByTourId = new HashMap<>();
        List<Tour> tours = user.getBucket().getTour();
        for (Tour tour : tours) {
            BucketDetailsDTO detail = mapByTourId.get(tour.getId());
            if (detail == null) {
                mapByTourId.put(tour.getId(), new BucketDetailsDTO(tour));
            } else {
                detail.setAmount(detail.getAmount() + 1);
                detail.setSum(detail.getSum() + tour.getPricePerPerson());
            }
        }
        bucketDTO.setDetails(new ArrayList<>(mapByTourId.values()));
        bucketDTO.aggregate();
        return bucketDTO;
    }

    @Override
    public void addBucketToOrder(String username) {

    }
}
