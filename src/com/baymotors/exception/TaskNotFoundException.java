package com.baymotors.exception;

public class TaskNotFoundException extends Exception {

    // Declaring serialVersionUID for compatibility during deserialization
    private static final long serialVersionUID = 1L;

    // Constructor that accepts a custom message
    public TaskNotFoundException(String message) {
        super(message);  // Calls the parent Exception constructor
    }
}
