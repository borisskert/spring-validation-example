package de.borisskert.spring.springvalidationexample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handleConstraintViolation(
            MethodArgumentNotValidException ex
    ) {
        return ResponseEntity.badRequest()
                .body(ex.getBindingResult()
                        .getFieldErrors());
    }
}
