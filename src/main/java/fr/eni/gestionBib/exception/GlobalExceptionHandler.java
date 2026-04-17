package fr.eni.gestionBib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(RuntimeException.class)
public ResponseEntity<?> handleRuntime(RuntimeException ex) {

    Map<String, String> error = new HashMap<>();
    error.put("message", ex.getMessage());

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(error);
}
}
