package com.example.services.bucket;

import com.example.dto.bucket.BucketDTO;
import com.example.models.Bucket;
import com.example.models.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Integer> tourIds);

    void addTour(Bucket bucket, List<Integer> tourIds);

    BucketDTO getBucketByUser(String name);

    void addBucketToOrder(String username);
}
