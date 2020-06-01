package com.contactRestAPI.contactAPI.service.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomContactException extends RuntimeException {
    public CustomContactException() {
    }

    public CustomContactException(String message) {
        super(message);
    }
}
