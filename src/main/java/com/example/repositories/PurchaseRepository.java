package com.example.repositories;

import com.example.models.Purchase;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface PurchaseRepository extends Repository<Purchase, Integer> {
    List<Purchase> findAll();
    void save(Purchase purchase);
}
