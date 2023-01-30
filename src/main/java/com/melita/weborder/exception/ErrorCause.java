package com.melita.weborder.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ErrorCause {

    private String message;

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	ErrorCause errorDto = (ErrorCause) o;
	return Objects.equals(message, errorDto.message);
    }

    @Override
    public int hashCode() {
	return Objects.hash(message);
    }

}
