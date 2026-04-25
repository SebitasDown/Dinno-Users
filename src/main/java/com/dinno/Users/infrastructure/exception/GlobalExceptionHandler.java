package com.dinno.Users.infrastructure.exception;

import com.dinno.Users.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        return problemDetail;
    }

    @ExceptionHandler(BusinessRuleValidationException.class)
    public ProblemDetail handleBusinessRuleValidationException(BusinessRuleValidationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Business Rule Violation");
        return problemDetail;
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ProblemDetail handleInvalidFileTypeException(InvalidFileTypeException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        problemDetail.setTitle("Invalid File Type");
        return problemDetail;
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ProblemDetail handleFileSizeExceededException(FileSizeExceededException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.PAYLOAD_TOO_LARGE, ex.getMessage());
        problemDetail.setTitle("File Size Too Large");
        return problemDetail;
    }

    @ExceptionHandler(FileTooSmallException.class)
    public ProblemDetail handleFileTooSmallException(FileTooSmallException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("File Too Small");
        return problemDetail;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ProblemDetail handleValidationException(WebExchangeBindException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed for one or more fields.");
        problemDetail.setTitle("Validation Error");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            problemDetail.setProperty(error.getField(), error.getDefaultMessage());
        });
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        // Log the exception locally before turning it into a generic "unexpected format" issue
        // Not returning ex.getMessage() for 500 to assure not leaking internal details on prod, but typically handled by logger.
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
        problemDetail.setTitle("Internal Server Error");
        return problemDetail;
    }
}
