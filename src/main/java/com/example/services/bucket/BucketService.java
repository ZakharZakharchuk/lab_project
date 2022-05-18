package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.models.Bucket;
import com.example.models.Order;
import com.example.models.User;

public interface BucketService {
    Bucket createBucket(User user);

    void addTour(Bucket bucket, Integer tourId);

    BucketDTO getBucketByUser(String name);

    Order addBucketToOrder(String username);
}
