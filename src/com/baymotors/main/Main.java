package com.baymotors.main;

import com.baymotors.model.*;
import com.baymotors.service.GarageSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialise the garage system
        GarageSystem garageSystem = new GarageSystem();

        // Create Manager and Mechanic (predefined for demonstration)
        Manager manager = new Manager("Jin", "jin@baymotors.com", "jin_011", "jin23");
        Mechanic mechanic = new Mechanic("Suga", "suga@baymotors.com", "suga_022", "suga456");

        // Reload customers and vehicles
        Customer customer1 = new Customer("Addy", "Addy@gmail.com", "1234567890");
        garageSystem.addCustomer(customer1);
        Vehicle vehicle1 = new Vehicle("YZC-0123", "Hyundai", "Sedan", customer1);
        garageSystem.addVehicle(vehicle1);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Main menu loop
        while (!exit) {
            System.out.println("\nWelcome to the Bay Motors System!");
            System.out.println("1. Log in as Manager");
            System.out.println("2. Log in as Mechanic");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    managerLogin(garageSystem, manager, scanner);
                    break;
                case 2:
                    mechanicLogin(garageSystem, mechanic, scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        scanner.close(); // Close scanner
    }

    private static void managerLogin(GarageSystem garageSystem, Manager manager, Scanner scanner) {
        System.out.print("\nEnter Manager username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Manager password: ");
        String password = scanner.nextLine();

        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            System.out.println("Manager login successful!");
            showManagerMenu(garageSystem, scanner);
        } else {
            System.out.println("Invalid credentials for Manager.");
        }
    }

    private static void mechanicLogin(GarageSystem garageSystem, Mechanic mechanic, Scanner scanner) {
        System.out.print("\nEnter Mechanic username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Mechanic password: ");
        String password = scanner.nextLine();

        // Find the mechanic
        Mechanic foundMechanic = garageSystem.findMechanicByUsername(username);
        if (foundMechanic != null) {
            System.out.println("Mechanic found: " + foundMechanic.getUsername());
            if (foundMechanic.getPassword().equals(password)) {
                System.out.println("Mechanic login successful!");
                showMechanicMenu(garageSystem, foundMechanic, scanner);
            } else {
                System.out.println("Invalid password for Mechanic.");
            }
        } else {
            System.out.println("Mechanic with username " + username + " not found.");
        }
    }




    private static void showManagerMenu(GarageSystem garageSystem, Scanner scanner) {
        boolean exitManagerMenu = false;
        while (!exitManagerMenu) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. Add Manufacturer");
            System.out.println("2. Add Part Supplier");
            System.out.println("3. Add Customer");
            System.out.println("4. View Customers");
            System.out.println("5. Allocate Task to Mechanic");
            System.out.println("6. Add Mechanic");
            System.out.println("7. Update Payment Status");
            System.out.println("8. Record Payment for Customer");
            System.out.println("9 Add Vehicle");
            System.out.println("10. Send Offers");
            System.out.println("11. Log out");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Manufacturer Name: ");
                    String manufacturerName = scanner.nextLine();
                    garageSystem.addManufacturer(manufacturerName);
                    break;
                case 2:
                    System.out.print("Enter Manufacturer Name: ");
                    String supplierManufacturer = scanner.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String supplierName = scanner.nextLine();
                    System.out.print("Enter Supplier Contact Info: ");
                    String supplierContact = scanner.nextLine();
                    garageSystem.addPartSupplier(supplierManufacturer, supplierName, supplierContact);
                    break;
                case 3:
                    addCustomer(garageSystem, scanner);
                    break;
                case 4:
                    viewCustomers(garageSystem, scanner);
                    break;
                case 5:
                    allocateTaskToMechanic(garageSystem, scanner);
                    break;
                case 6:
                	promptAddMechanic(garageSystem, scanner);
                    break;
                case 7:
                    updatePaymentStatus(garageSystem, scanner);
                    break;
                case 8:
                    recordPaymentForCustomer(garageSystem, scanner);
                    break;
                case 9:
                	addVehicleByManager(garageSystem, scanner);
                    break;
                case 10:
                    sendOffers(garageSystem);
                    break;
                case 11:
                    exitManagerMenu = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
    
    private static void recordPaymentForCustomer(GarageSystem garageSystem, Scanner scanner) {
        System.out.print("\nEnter Vehicle License Plate to record payment: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = garageSystem.findVehicleByLicensePlate(licensePlate);

        if (vehicle != null) {
            System.out.print("Enter the payment amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Payment payment = new Payment(amount, false); // Create a payment object with 'unpaid' status
            garageSystem.addPayment(vehicle, payment);
            System.out.println("Payment of $" + amount + " has been recorded for vehicle " + licensePlate);
        } else {
            System.out.println("Vehicle not found.");
        }
    }
    
    private static void promptAddMechanic(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("\n--- Add Mechanic ---");
        System.out.print("Enter Mechanic Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Mechanic Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Mechanic Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Mechanic Password: ");
        String password = scanner.nextLine();

        // Create the Mechanic object and add it to the GarageSystem
        Mechanic mechanic = new Mechanic(name, email, username, password);
        garageSystem.addMechanic(mechanic);
        System.out.println("Notification: Mechanic " + name + " has been added successfully.");
    }

    private static void updatePaymentStatus(GarageSystem garageSystem, Scanner scanner) {
        System.out.print("\nEnter Vehicle License Plate to update payment status: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = garageSystem.findVehicleByLicensePlate(licensePlate);

        if (vehicle != null) {
            Payment payment = garageSystem.getPayment(vehicle);
            if (payment != null && !payment.isPaid()) {
                garageSystem.markPaymentAsPaid(vehicle);
                System.out.println("Payment status updated successfully.");
            } else if (payment == null) {
                System.out.println("No payment record found for this vehicle.");
            } else {
                System.out.println("Payment is already marked as paid.");
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private static void viewCustomers(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("\n--- Registered Customers ---");
        garageSystem.getRegisteredCustomers().forEach(customer ->
                System.out.println(customer.getName() + " - " + customer.getEmail() + " - " + customer.getPhoneNumber()));

        System.out.println("\n--- Unregistered Customers ---");
        garageSystem.getUnregisteredCustomers().forEach(customer ->
                System.out.println(customer.getName()));

        System.out.print("\nDo you want to register an unregistered customer? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the name of the customer you want to register: ");
            String customerName = scanner.nextLine();

            Customer customer = garageSystem.findUnregisteredCustomerByName(customerName);
            if (customer != null) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();
                customer.register(email, phoneNumber);
                System.out.println("Notification: Customer " + customer.getName() + " has been successfully registered.");
            } else {
                System.out.println("Customer not found.");
            }
        }
    }

    private static void addCustomer(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("\nEnter Customer Information");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Do you want to register the customer? (yes/no): ");
        String registeredResponse = scanner.nextLine();

        if (registeredResponse.equalsIgnoreCase("yes")) {
            System.out.print("Enter Customer Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Customer Phone Number: ");
            String phoneNumber = scanner.nextLine();
            Customer customer = new Customer(name, email, phoneNumber);
            garageSystem.addCustomer(customer);
        } else {
            System.out.print("Enter Customer Phone Number (required for notifications): ");
            String phoneNumber = scanner.nextLine();
            Customer customer = new Customer(name);
            customer.setPhoneNumber(phoneNumber);
            garageSystem.addCustomer(customer);
        }
    }

    private static void addVehicleByManager(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("\n--- Add Vehicle ---");
        System.out.print("Enter Vehicle License Plate: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Enter Vehicle Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Vehicle Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Owner Name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Enter Owner Email: ");
        String ownerEmail = scanner.nextLine();
        System.out.print("Enter Owner Phone Number: ");
        String ownerPhoneNumber = scanner.nextLine();

        // Call the method in GarageSystem
        garageSystem.addVehicleByManager(licensePlate, make, model, ownerName, ownerEmail, ownerPhoneNumber);
    }

    private static void sendOffers(GarageSystem garageSystem) {
        System.out.println("\n--- Sending Offers ---");
        Offer registrationOffer = new Offer("Get 20% off for first-time registration!", "REGISTER20");
        Offer discountOffer = new Offer("Enjoy 15% off on your next service!", "DISCOUNT15");

        garageSystem.getUnregisteredCustomers().forEach(customer -> {
            customer.receiveNotification("Special Offer: " + registrationOffer.getDescription() +
                    " Use code: " + registrationOffer.getDiscountCode());
        });

        garageSystem.getRegisteredCustomers().forEach(customer -> {
            customer.receiveNotification("Thank you for being with us! " + discountOffer.getDescription() +
                    " Use code: " + discountOffer.getDiscountCode());
        });

        System.out.println("Offers sent successfully.");
    }

    private static void allocateTaskToMechanic(GarageSystem garageSystem, Scanner scanner) {
        System.out.println("\n--- Select a Task Type ---");
        System.out.println("1. Oil Change");
        System.out.println("2. Tire Replacement");
        System.out.println("3. Brake Inspection");
        System.out.println("4. Engine Diagnostics");
        System.out.print("Please select the task type: ");

        int taskChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String taskDetails = switch (taskChoice) {
            case 1 -> "Oil Change";
            case 2 -> "Tire Replacement";
            case 3 -> "Brake Inspection";
            case 4 -> "Engine Diagnostics";
            default -> {
                System.out.println("Invalid choice, defaulting to 'Oil Change'.");
                yield "Oil Change";
            }
        };

        System.out.print("Enter Task Priority (1-High, 2-Medium, 3-Low): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Vehicle License Plate: ");
        String licensePlate = scanner.nextLine();
        Vehicle vehicle = garageSystem.findVehicleByLicensePlate(licensePlate);

        if (vehicle != null) {
            System.out.print("Enter Mechanic Username: ");
            String mechanicUsername = scanner.nextLine();
            Mechanic mechanic = garageSystem.findMechanicByUsername(mechanicUsername); // Add a method in GarageSystem to find a mechanic by username

            if (mechanic != null) {
                garageSystem.allocateTaskToMechanic(taskDetails, priority, vehicle, mechanic);
            } else {
                System.out.println("Mechanic not found!");
            }
        } else {
            System.out.println("Vehicle not found!");
        }
    }



    private static void showMechanicMenu(GarageSystem garageSystem, Mechanic mechanic, Scanner scanner) {
        boolean exitMechanicMenu = false;
        while (!exitMechanicMenu) {
            System.out.println("\n--- Mechanic Menu ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Complete Task");
            System.out.println("3. Log out");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    mechanic.viewTasks();
                    break;
                case 2:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    mechanic.completeTask(taskId);
                    break;
                case 3:
                    exitMechanicMenu = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
