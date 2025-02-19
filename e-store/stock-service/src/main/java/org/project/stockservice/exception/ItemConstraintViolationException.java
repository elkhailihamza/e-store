package org.project.stockservice.exception;

public class ItemConstraintViolationException extends RuntimeException {
    public ItemConstraintViolationException(String message) {
        super(message);
    }
}
