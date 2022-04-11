package com.example.controllers;

import com.example.dao.TourDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourDAO tourDAO;

    @Autowired
    public TourController(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }
    @GetMapping("/show")
    public String showAllTours(Model model){
        model.addAttribute("tours", tourDAO.findAllTours());
        return "showTours";
    }
}
