package com.melita.weborder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestNotValidException extends RuntimeException{

    public RequestNotValidException(final String message) {
	super(message);
    }

}
