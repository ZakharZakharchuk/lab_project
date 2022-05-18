package com.example.dto.bucket;

import com.example.models.Tour;

public class BucketDetailsDTO {
    private String name;
    private int tourId;
    private int price;
    private int amount;
    private int sum;

    public BucketDetailsDTO(Tour tour, int amount) {
        this(tour.getName(), tour.getId(), tour.getPricePerPerson(), amount);
    }

    private BucketDetailsDTO(String name, int tourId, int price, int amount) {
        this.name = name;
        this.tourId = tourId;
        this.price = price;
        this.amount = amount;
        this.sum = price * amount;
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
