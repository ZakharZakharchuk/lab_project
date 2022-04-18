package com.example.dto.tour;

public class TourDTO {
    private String name;
    private int pricePerPerson;

    public TourDTO(String name, int pricePerPerson) {
        this.name = name;
        this.pricePerPerson = pricePerPerson;
    }

    public TourDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }
}
