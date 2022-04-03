package com.project.stockexchangeappbackend.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidInputDataException extends RuntimeException {

    private final Map<String, ? extends Object> errors;

    public InvalidInputDataException(String msg, Map<String, ? extends Object> errors) {
        super(msg);
        this.errors = errors;
    }

}
