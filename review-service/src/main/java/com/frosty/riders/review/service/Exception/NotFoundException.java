package com.frosty.riders.review.service.Exception;

import com.frosty.riders.review.service.model.response.FailureResponse;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final FailureResponse failureResponse;

    public NotFoundException(FailureResponse failureResponse) {
        this.failureResponse = failureResponse;
    }

}
