package com.project.stockexchangeappbackend.exception;

import org.springframework.security.core.AuthenticationException;

public class UserBannedException extends AuthenticationException {

    public UserBannedException(String msg) {
        super(msg);
    }

}
