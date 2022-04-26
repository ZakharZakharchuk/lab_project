package com.example.repositories;

import com.example.models.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BucketRepository extends JpaRepository<Bucket, Integer> {

}
