package com.baymotors.model;

import com.baymotors.service.GarageSystem;
import java.util.Scanner;

public class Manager extends User {

    public Manager(String name, String email, String username, String password) {
        super(name, email, username, password);
    }

    // Add a new customer with full details
    public void addCustomer(GarageSystem garageSystem, String name, String email, String phoneNumber) {
        Customer customer = new Customer(name, email, phoneNumber);
        garageSystem.addCustomer(customer);
        System.out.println("Manager " + getName() + " added customer " + name);
    }

    // Add a walk-in customer
    public void addWalkInCustomer(GarageSystem garageSystem, String name) {
        Customer customer = new Customer(name);
        garageSystem.addCustomer(customer);
        System.out.println("Manager " + getName() + " added walk-in customer " + name);
    }

    // View and register customers (if necessary)
    public void viewCustomers(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("--- Registered Customers ---");

        // Display registered customers
        boolean hasUnregistered = false;
        for (Customer customer : garageSystem.getCustomers()) {
            if (customer.isRegistered()) {
                System.out.println("Name: " + customer.getName() + ", Email: " + customer.getEmail());
            } else {
                hasUnregistered = true; // Track if there are unregistered customers
            }
        }

        // Display unregistered customers
        if (hasUnregistered) {
            System.out.println("\n--- Unregistered Customers ---");
            for (Customer customer : garageSystem.getCustomers()) {
                if (!customer.isRegistered()) {
                    System.out.println("Name: " + customer.getName());
                }
            }

            // Ask if the manager wants to register an unregistered customer
            System.out.println("\nDo you want to register an unregistered customer? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Enter the name of the customer you want to register:");
                String customerName = scanner.nextLine();

                for (Customer customer : garageSystem.getCustomers()) {
                    if (customer.getName().equals(customerName) && !customer.isRegistered()) {
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter phone number:");
                        String phoneNumber = scanner.nextLine();
                        customer.registerCustomerByManager(email, phoneNumber);
                        System.out.println("Customer registered successfully.");
                        return;
                    }
                }
                System.out.println("Customer not found.");
            }
        }
    }

    // Mark payment as paid for a vehicle
    public void markPaymentAsPaid(GarageSystem garageSystem, Vehicle vehicle) {
        Payment payment = garageSystem.getPayment(vehicle);

        // If payment exists and is not yet paid
        if (payment != null && !payment.isPaid()) {
            payment.markAsPaid();
            System.out.println("Payment for vehicle " + vehicle.getLicensePlate() + " is marked as paid.");

            Customer customer = vehicle.getOwner();
            if (customer != null) {
                // Send appropriate notification based on customer registration status
                if (customer.isRegistered()) {
                    Notification.sendNotification(customer.getEmail(), "Your payment has been successfully processed.");
                } else {
                    if (customer.getPhoneNumber() != null) {
                        Notification.sendWalkInNotification(customer.getPhoneNumber(), "Your payment has been successfully processed.");
                    } else {
                        System.out.println("Unregistered customer does not have a phone number for SMS notification.");
                    }
                }
            }
        } else {
            System.out.println("The vehicle " + vehicle.getLicensePlate() + " is already paid or no payment found.");
        }
    }
}