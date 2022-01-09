package com.jonathan.signinsignup.common;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError extends Error {

    private ErrorData error = new ErrorData();

    private ApiError() {
        error.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.error.setStatus(status);
        this.error.setMessage(message);
    }

    public ApiError(HttpStatus status, String message, String debugMessage) {
        this();
        this.error.setStatus(status);
        this.error.setMessage(message);
        this.error.setDebugMessage(debugMessage);
    }

    public ApiError(HttpStatus status, String message, List<FieldError> fieldErrors) {
        this();
        this.error.setStatus(status);
        this.error.setMessage(message);
        this.error.setErrors(fieldErrors);
    }

    @Data
    static class ErrorData {
        private HttpStatus status;
        private String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String debugMessage;
        private List<FieldError> errors = new ArrayList<>();

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM hh:mm:ss")
        private LocalDateTime timestamp;
    }

    @Data
    static class FieldError {
        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }
}


