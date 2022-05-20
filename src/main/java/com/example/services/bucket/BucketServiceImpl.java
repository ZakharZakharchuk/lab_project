package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.dto.bucket.BucketDetailsDTO;
import com.example.models.*;
import com.example.repositories.BucketRepository;
import com.example.repositories.OrderRepository;
import com.example.repositories.TourRepository;
import com.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, TourRepository tourRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.bucketRepository = bucketRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Bucket createBucket(User user) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        return bucket;
    }

    @Override
    public void addTour(Bucket bucket, Integer tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("This tour no longer exists."));

        bucket.getTours().add(tour);
        bucketRepository.save(bucket);
    }

    @Override
    public void removeTour(Bucket bucket, Integer tourId) {
        List<Tour> tours = bucket.getTours().stream()
                .filter(tour -> tour.getId() == tourId)
                .toList();

        for (Tour tour : tours) {
            bucket.getTours().remove(tour);
        }

        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userRepository.findFirstByName(name);
        if (user == null || user.getBucket() == null)
            return new BucketDTO();

        List<Tour> tours = user.getBucket().getTours();

        Map<Tour, Long> groupedTours = tours.stream().collect(Collectors
                .groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        List<BucketDetailsDTO> listOfDetails = groupedTours.keySet().stream()
                .map(tour -> new BucketDetailsDTO(tour, groupedTours.get(tour).intValue()))
                .collect(Collectors.toList());

        return new BucketDTO(listOfDetails);
    }

    @Override
    public Order addBucketToOrder(String username) {
        User user = userRepository.findFirstByName(username);
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
        orderRepository.save(order);
        bucket.getTours().clear();
        bucketRepository.save(bucket);
        return order;
    }
}
