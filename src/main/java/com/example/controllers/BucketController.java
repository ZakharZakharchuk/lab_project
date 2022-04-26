package com.example.controllers;

import com.example.dto.bucket.BucketDTO;
import com.example.models.Bucket;
import com.example.services.bucket.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/bucket")
    public String showBucket(Model model, Principal principal) {
        Bucket bucket = bucketService.getBucketByUser(principal.getName());
        model.addAttribute("bucket", bucket);
        return "bucket";
    }
}
