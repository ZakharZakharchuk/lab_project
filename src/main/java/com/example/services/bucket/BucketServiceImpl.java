package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.dto.bucket.BucketDetailsDTO;
import com.example.models.*;
import com.example.repositories.BucketRepository;
import com.example.repositories.TourRepository;
import com.example.services.order.OrderService;
import com.example.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final TourRepository tourRepository;
    private final UserService userService;
    private final OrderService orderService;

    public BucketServiceImpl(BucketRepository bucketRepository, TourRepository tourRepository, UserService userService, OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.tourRepository = tourRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public Bucket createBucket(User user, List<Integer> tourIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Tour> tours = getCollectionByTourIds(tourIds);
        bucket.setTours(tours);
        return bucket;
    }

    private List<Tour> getCollectionByTourIds(List<Integer> tourIds) {
        return tourRepository.findByIdIn(tourIds);
    }

    @Override
    public void addTour(Bucket bucket, List<Integer> tourIds) {
        List<Tour> tours = bucket.getTours();
        List<Tour> newTourList = tours == null ? new ArrayList<>() : new ArrayList<>(tours);
        newTourList.addAll(getCollectionByTourIds(tourIds));
        bucket.setTours(newTourList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null)
            return new BucketDTO();

        BucketDTO bucketDTO = new BucketDTO();
        Map<Integer, BucketDetailsDTO> mapByTourId = new HashMap<>();
        List<Tour> tours = user.getBucket().getTours();
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
    public Order addBucketToOrder(String username) {
        User user = userService.findByName(username);
        Bucket bucket = user.getBucket();
        Order order = new Order();
        order.setUser(user);

        Map<Tour, Long> tourWithAmount = bucket.getTours().stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        List<OrderDetails> orderDetails = tourWithAmount.entrySet().stream()
                .map(x -> new OrderDetails(order, x.getKey(), x.getValue().intValue())).toList();
        int sum = orderDetails.stream().mapToInt(x -> x.getTour().getPricePerPerson() * x.getAmount()).sum();
        order.setOrderDetails(orderDetails);
        order.setSum(sum);
        orderService.save(order);
        bucket.getTours().clear();
        bucketRepository.save(bucket);
        return order;
    }
}
