package com.example.dto.bucket;

import java.util.ArrayList;
import java.util.List;

public class BucketDTO {
    private int amount;
    private int sum;
    private List<BucketDetailsDTO> details = new ArrayList<>();

    public void aggregate() {
        this.amount = details.size();
        this.sum = details.stream()
                .map(x -> x.getSum())
                .mapToInt(x -> x)
                .sum();
    }

    public BucketDTO(int amount, int sum, List<BucketDetailsDTO> details) {
        this.amount = amount;
        this.sum = sum;
        this.details = details;
    }

    public BucketDTO() {
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

    public List<BucketDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<BucketDetailsDTO> details) {
        this.details = details;
    }
}
