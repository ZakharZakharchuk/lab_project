package com.example;

import com.example.dao.PurchaseDAO;
import com.example.dao.TourDAO;
import com.example.dao.UserDAO;
import com.example.models.Purchase;
import com.example.models.Tour;
import com.example.models.User;

import java.sql.SQLException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        TourDAO tourDAO = new TourDAO();
        UserDAO userDAO = new UserDAO();
        User user1= new User("Zahar","email");
        User user2 = new User("Mike", "email2");
        Tour tour = new Tour("Tour", 111111);
        tourDAO.insertTour(tour);
        userDAO.insertUser(user2);
        Purchase purchase1 = new Purchase("P1", user2.getId(), tour.getId());
        PurchaseDAO purchaseDAO= new PurchaseDAO();
        purchaseDAO.insertPurchase(purchase1);
        System.out.println(tourDAO.findAllTours());
        System.out.println(userDAO.findAllUsers());
        System.out.println(purchaseDAO.findAllPurchases());
    }
}
