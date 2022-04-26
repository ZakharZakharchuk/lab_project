package com.example.dto.bucket;

import java.util.ArrayList;
import java.util.List;

public class BucketDTO {
    private int amount;
    private int sum;
    private List<TourDetailsDTO> details = new ArrayList<>();

    public void aggregate() {
        this.amount = details.size();
        this.sum = details.stream()
                .map(x -> x.getSum())
                .mapToInt(x -> x)
                .sum();
    }
}
