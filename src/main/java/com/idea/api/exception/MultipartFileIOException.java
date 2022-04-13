package com.idea.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MultipartFileIOException extends ResponseStatusException {

    public MultipartFileIOException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
