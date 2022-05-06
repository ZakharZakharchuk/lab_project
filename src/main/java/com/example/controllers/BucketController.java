package com.example.controllers;

import com.example.dto.bucket.BucketDTO;
import com.example.models.Order;
import com.example.services.bucket.BucketService;
import com.example.services.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BucketController {
    private final BucketService bucketService;
    private final OrderService orderService;

    public BucketController(BucketService bucketService, OrderService orderService) {
        this.bucketService = bucketService;
        this.orderService = orderService;
    }

    @GetMapping("/bucket")
    public String showBucket(Model model, Principal principal) {
        BucketDTO bucket = bucketService.getBucketByUser(principal.getName());
        model.addAttribute("bucket", bucket);
        return "bucket/bucket";
    }

    @PostMapping("/order")
    public String AddBucketToOrder(Model model, Principal principal) {
        Order order = bucketService.addBucketToOrder(principal.getName());
        model.addAttribute("order", order);
        return "order/orders";
    }
}
