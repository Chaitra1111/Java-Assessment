package com.baymotors.model;

public class Notification {

    // Send notification to registered customers via email (simulated on the console)
    public static void sendNotification(String email, String message) {
        if (email == null || email.isEmpty()) {
            System.out.println("Invalid email address.");
            return;
        }
        
        // Simulate sending an email by printing to the console
        System.out.println("Sending email to " + email + ": " + message);
    }

    // Send notification to unregistered (walk-in) customers via SMS (simulated on the console)
    public static void sendWalkInNotification(String phoneNumber, String message) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Invalid phone number.");
            return;
        }

        // Simulate sending an SMS by printing to the console
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    // Method to send a task completion notification
    public static void sendTaskCompletionNotification(Customer customer, Vehicle vehicle) {
        String message = "Your vehicle " + vehicle.getLicensePlate() + " is ready for pickup.";
        
        if (customer.isRegistered()) {
            // If customer is registered, simulate sending an email
            sendNotification(customer.getEmail(), message);
        } else {
            // If customer is not registered, simulate sending an SMS
            sendWalkInNotification(customer.getPhoneNumber(), message);
        }
    }

    // Method to display a notification when a mechanic completes a task
    public static void taskCompletedNotification(Mechanic mechanic, Task task) {
        String message = "Task completed: " + task.getTaskDetails() + " for vehicle " + task.getVehicle().getLicensePlate();
        
        // Display message on the console indicating task completion
        System.out.println("Notification for mechanic " + mechanic.getName() + ": " + message);
    }
}