package com.example.dto.bucket;

import com.example.models.Tour;

public class TourDetailsDTO {
    private String name;
    private int tourId;
    private int price;
    private int amount;
    private int sum;

    public TourDetailsDTO(Tour tour) {
        this.name = tour.getName();
        this.tourId = tour.getId();
        this.price = tour.getPricePerPerson();
        this.amount = 1;
        this.sum = amount * this.price;
    }

    public TourDetailsDTO(String name, int tourId, int price, int amount, int sum) {
        this.name = name;
        this.tourId = tourId;
        this.price = price;
        this.amount = amount;
        this.sum = sum;
    }

    public TourDetailsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
