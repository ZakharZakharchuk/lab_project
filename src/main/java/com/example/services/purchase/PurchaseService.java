package com.example.services.purchase;

import com.example.models.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    void save(Purchase purchase);
}
