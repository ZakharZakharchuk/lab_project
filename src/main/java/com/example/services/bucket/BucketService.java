package com.example.services.bucket;

import com.example.models.Bucket;
import com.example.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Integer> tourIds);

    void addTour(Bucket bucket, List<Integer> tourIds);

    Bucket getBucketByUser(String name);
}
