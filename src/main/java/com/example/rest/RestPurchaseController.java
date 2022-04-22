package com.example.rest;

import com.example.models.Purchase;
import com.example.services.purchase.PurchaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/purchase")
public class RestPurchaseController {
    private final PurchaseService purchaseService;

    public RestPurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping()
    public List<Purchase> showAllTours() {
        return purchaseService.findAll();
    }
}
