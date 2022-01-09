package com.jonathan.signinsignup.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestApiExceptionHandler {

    @Value("${spring.profiles.active}")
    String activeSpringProfile;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ApiError.FieldError> fieldErrors = exception.getFieldErrors().stream().map(i -> {
            return new ApiError.FieldError(i.getField(), i.getDefaultMessage());
        }).collect(Collectors.toList());

        return buildResponseEntityApiError(new ApiError(HttpStatus.BAD_REQUEST, "Request payload validation failed", fieldErrors));
    }


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ApiError.class)
    protected ResponseEntity<Object> handleApiError(
            ApiError apiError) {
        return buildResponseEntityApiError(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid request");
        return buildResponseEntityApiError(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        boolean isProd = "prod".equals(activeSpringProfile);
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please try again later.",
                isProd ? null : e.toString()
        );

        return buildResponseEntityApiError(apiError);
    }

    private ResponseEntity<Object> buildResponseEntityApiError(ApiError apiError) {
        return new ResponseEntity<Object>(apiError.getError(), apiError.getError().getStatus());
    }

}