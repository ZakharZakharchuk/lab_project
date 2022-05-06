package com.example.repositories;

import com.example.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {
//    @Query("delete from Bucket.tours bucket where bucket.id=:tourId")
//    void removeTourById(@Param("tourId") int tourId, @Param("bucketId") int bucketId);

    List<Tour> findByIdIn(List<Integer> tourIds);
}
