package org.project.stockservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionDetails.builder()
                        .message(ex.getMessage())
                        .date(new Date())
                        .description(request.getDescription(false))
                        .build()
        );
    }

    @ExceptionHandler(ItemConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleItemConstraintViolationException(ItemConstraintViolationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionDetails.builder()
                        .message(ex.getMessage())
                        .date(new Date())
                        .description(request.getDescription(false))
                        .build()
        );
    }
}
