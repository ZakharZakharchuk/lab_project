package com.example.dto.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketDTO {

    private final List<BucketDetailsDTO> details;

    public BucketDTO(List<BucketDetailsDTO> details) {
        this.details = details;
    }

    public BucketDTO() {
        this(new ArrayList<>());
    }

    public int getAmount() {
        return details.size();
    }

    public int getSum() {
        // we can put this logic here because we don't need to call it many times;
        // alternatively, we can put it in a variable, as before,
        // calculating the value in the constructor;
        return details.stream()
                .map(BucketDetailsDTO::getSum)
                .mapToInt(x -> x)
                .sum();
    }

    public List<BucketDetailsDTO> getDetails() {
        return Collections.unmodifiableList(details);
    }
}
