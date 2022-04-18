package com.example.controllers;

import com.example.models.Purchase;
import com.example.repositories.PurchaseRepository;
import com.example.services.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping()
    public String showAllTours(Model model) {
        model.addAttribute("purchases", purchaseService.findAll());
        return "purchase/show";
    }

    @GetMapping("/new")
    public String newPurchase(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase/new";
    }

    @PostMapping()
    public String savePurchase(Purchase purchase) {
        purchaseService.save(purchase);
        return "redirect:/purchases";
    }
}
