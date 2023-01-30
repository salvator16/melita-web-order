package com.melita.weborder.exception;

import org.springframework.http.HttpStatus;

public class CommonAuthException extends RuntimeException {

    private final HttpStatus status;

    public CommonAuthException() {
	this( "Authentication Failed", HttpStatus.UNAUTHORIZED);
    }

    public CommonAuthException(String error, HttpStatus status) {
	super(error);
	this.status = status;
    }

    public HttpStatus getStatus() {
	return this.status;
    }

}
