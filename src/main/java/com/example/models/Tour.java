package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tours")
public class Tour {
    @Id
    private int id;
    private String name;
    private int pricePerPerson;

    public Tour() {
    }

    public Tour(String name, int pricePerPerson) {
        this.name = name;
        this.pricePerPerson = pricePerPerson;
    }

    public Tour(int id, String name, int pricePerPerson) {
        this.id = id;
        this.name = name;
        this.pricePerPerson = pricePerPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (id != tour.id) return false;
        if (pricePerPerson != tour.pricePerPerson) return false;
        return name.equals(tour.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + pricePerPerson;
        return result;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerPerson=" + pricePerPerson +
                '}';
    }
}
