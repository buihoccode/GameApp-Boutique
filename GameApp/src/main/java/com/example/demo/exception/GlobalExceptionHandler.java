package com.example.demo.exception;

import com.example.demo.dto.GameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GameResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GameResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(PlateformeAlreadyExistsException.class)
    public ResponseEntity<GameResponse<Void>> handleAlreadyExists(PlateformeAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(GameResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GameResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GameResponse.error("Erreur de validation des données"));
    }
}