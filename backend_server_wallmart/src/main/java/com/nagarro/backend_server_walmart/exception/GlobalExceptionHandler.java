package com.nagarro.backend_server_walmart.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

 // Handling specific ItemNotFoundException
 @ExceptionHandler(ItemNotFoundException.class)
 public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException ex) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
 }

 // Handling all other exceptions
 @ExceptionHandler(Exception.class)
 public ResponseEntity<String> handleGeneralException(Exception ex) {
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
 }
}
