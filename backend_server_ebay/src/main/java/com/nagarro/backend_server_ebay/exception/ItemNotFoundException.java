package com.nagarro.backend_server_ebay.exception;

public class ItemNotFoundException extends RuntimeException {
 public ItemNotFoundException(String message) {
     super(message);
 }
}
