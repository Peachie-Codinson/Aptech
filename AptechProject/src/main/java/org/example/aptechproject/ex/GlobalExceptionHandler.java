package org.example.aptechproject.ex;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        HttpStatus status = ex.getStatus();
        ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());
        return ResponseEntity.status(status)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> messages = new HashMap<>();
//        ex.getAllErrors().get(0).getDefaultMessage()
        List<ObjectError> errors = ex.getAllErrors();
        for (ObjectError error : errors) {
            FieldError errrrr = (FieldError) error;
            messages.put(errrrr.getField(), errrrr.getDefaultMessage());
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(status.value(), "Co loi bad request roi, check details trong messages", messages);
        return ResponseEntity.status(status)
                .body(error);
    }
}

