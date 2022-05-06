package com.example.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "buckets_tours",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<Tour> tours;

    public Bucket(int id, User user, List<Tour> tour) {
        this.id = id;
        this.user = user;
        this.tours = tour;
    }

    public Bucket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tour) {
        this.tours = tour;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", user=" + user +
                ", tour=" + tours +
                '}';
    }
}
