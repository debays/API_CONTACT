package com.contactRestAPI.contactAPI.service.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomCompanyException extends RuntimeException{
    public CustomCompanyException() {
    }

    public CustomCompanyException(String message) {
        super(message);
    }
}
