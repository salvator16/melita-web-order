package com.melita.weborder.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  HTTP status code NOT_FOUND (404)
     *
     * @param e Exception to be handled
     * @return {@link ErrorCause} containing the error message
     *
     */

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorCause> handleProductNotFoundException(ProductNotFoundException e) {
	final ErrorCause err = new ErrorCause(e.getMessage());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /**
     *  HTTP status code NOT_FOUND (404)
     *
     * @param e Exception to be handled
     * @return {@link ErrorCause} containing the error message
     *
     */

    @ExceptionHandler(RequestNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorCause> handleRequestNotValidException(RequestNotValidException e) {
	final ErrorCause err = new ErrorCause(e.getMessage());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /**
     * @param e implemented for sample usage just to handle all the other exceptions
     * @return sample error message
     */


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorCause> handleRuntimeException(RuntimeException e) {
	log.error(e.getMessage());
	final ErrorCause err = new ErrorCause(e.getMessage());
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }


}
