package com.nguyen1207.vishbe.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public abstract class CustomException extends ResponseStatusException {
    public CustomException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
