package com.baymotors.model;

import com.baymotors.util.ValidationUtil;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isRegistered;

    // Constructor for unregistered customer
    public Customer(String name) {
        validateName(name);
        this.name = name;
        this.email = null;
        this.phoneNumber = null;
        this.isRegistered = false;
    }

    // Constructor for registered customer with name, email, and phone number
    public Customer(String name, String email, String phoneNumber) {
        validateName(name);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isRegistered = true;
    }

    // Constructor for customer with name and email (optional phone number)
    public Customer(String name, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
        this.phoneNumber = null;
        this.isRegistered = true;
    }

    // Validate name
    private void validateName(String name) {
        if (!ValidationUtil.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name. Only alphabets are allowed.");
        }
    }

    // Validate email
    private void validateEmail(String email) {
        if (!ValidationUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    // Validate phone number
    private void validatePhoneNumber(String phoneNumber) {
        if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

    // Method to register the customer (if not already registered)
    public void register(String email, String phoneNumber) {
        if (isRegistered) {
            System.out.println("Customer " + name + " is already registered.");
            return;
        }
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isRegistered = true;
        System.out.println("Customer " + name + " is now registered.");
        receiveNotification("Welcome, " + name + "! Thank you for registering with us.");
    }

    // Method to receive notifications
    public void receiveNotification(String message) {
        if (isRegistered) {
            if (this.email != null) {
                System.out.println("Notification sent to " + name + " via email: " + message);
            } else {
                System.out.println("No email available to notify " + name);
            }
        } else {
            if (this.phoneNumber != null) {
                System.out.println("Notification sent to " + name + " via SMS: " + message);
            } else {
                System.out.println("No contact information available to notify " + name);
            }
        }
    }

    // Register customer if not registered, will be called from the Manager class
    public void registerCustomerByManager(String email, String phoneNumber) {
        if (!this.isRegistered) {
            register(email, phoneNumber);
        } else {
            System.out.println("Customer is already registered.");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    @Override
    public String toString() {
        if (isRegistered) {
            return name + " - " + email + " - " + phoneNumber;
        } else {
            return name;
        }
    }
}
