package com.example.models;


import javax.persistence.*;

@Entity(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "tours_id")
    private int tourId;
    @Column(name = "users_id")
    private int userId;

    public Purchase(int id, String name, int tourId, int userId) {
        this.id = id;
        this.name = name;
        this.tourId = tourId;
        this.userId = userId;
    }

    public Purchase(String name, int tourId, int userId) {
        this.name = name;
        this.tourId = tourId;
        this.userId = userId;
    }

    public Purchase(String name) {
        this.name = name;
    }

    public Purchase() {
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

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (id != purchase.id) return false;
        if (tourId != purchase.tourId) return false;
        if (userId != purchase.userId) return false;
        return name.equals(purchase.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + tourId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tourId=" + tourId +
                ", userId=" + userId +
                '}';
    }
}
