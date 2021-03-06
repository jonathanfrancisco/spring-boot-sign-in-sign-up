package com.jonathan.signinsignup.common;

import com.jonathan.signinsignup.common.errors.ApiErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestApiExceptionHandler {

    @Value("${spring.profiles.active}")
    private String activeSpringProfile;

    @ExceptionHandler(ApiErrorException.class)
    protected ResponseEntity<Object> handleApiErrorException(
            ApiErrorException apiError) {
        return buildResponseEntityApiError(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ApiErrorException.FieldError> fieldErrors = exception.getFieldErrors().stream().map(i -> {
            return new ApiErrorException.FieldError(i.getField(), i.getDefaultMessage());
        }).collect(Collectors.toList());

        return buildResponseEntityApiError(new ApiErrorException(HttpStatus.BAD_REQUEST, "Request payload validation failed", fieldErrors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ApiErrorException apiError = new ApiErrorException(HttpStatus.BAD_REQUEST, "Malformed or missing request payload");
        return buildResponseEntityApiError(apiError);
    }


    @ExceptionHandler({Exception.class, Error.class})
    public ResponseEntity<Object> globalExceptionHandler(Exception e) {
        boolean isProd = "prod".equals(activeSpringProfile);

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter( writer );
        e.printStackTrace( printWriter );
        printWriter.flush();

        String stackTrace = writer.toString();
        System.out.println(stackTrace);


        ApiErrorException apiError = new ApiErrorException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please try again later.",
                isProd ? null : e.toString()
        );

        return buildResponseEntityApiError(apiError);
    }

    private ResponseEntity<Object> buildResponseEntityApiError(ApiErrorException apiError) {
        return new ResponseEntity<Object>(apiError.getError(), apiError.getError().getStatus());
    }

}