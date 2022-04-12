package com.example.repositories;

import com.example.models.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findAll();
}
