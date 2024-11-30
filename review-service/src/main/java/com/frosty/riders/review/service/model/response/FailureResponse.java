package com.frosty.riders.review.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class FailureResponse implements Serializable {

    private String errorMessage;
    private HttpStatus status;

}