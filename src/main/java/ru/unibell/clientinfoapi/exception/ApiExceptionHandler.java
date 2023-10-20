package ru.unibell.clientinfoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleException(BaseException ex) {
        Error error = ex.getError();
        HttpStatus status = error.getStatus();
        ApiError apiError = new ApiError(LocalDateTime.now(), error.getCode(), status, error.getMessage());
        return ResponseEntity.status(status).body(apiError);
    }

}
