package com.baymotors.service;

import com.baymotors.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GarageSystem {

    private final ArrayList<Manufacturer> manufacturers = new ArrayList<>();
    private final ArrayList<PartSupplier> suppliers = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final List<Mechanic> mechanics = new ArrayList<>();
    private final Map<Vehicle, Payment> vehiclePayments = new HashMap<>();
    private final List<Offer> offers = new ArrayList<>(); // To store offers

    // *** Manufacturer Methods ***
    public void addManufacturer(String manufacturerName) {
        Manufacturer manufacturer = new Manufacturer(manufacturerName);
        manufacturers.add(manufacturer);
        System.out.println("Manufacturer " + manufacturerName + " added.");
    }

    private Manufacturer findManufacturerByName(String name) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getName().equalsIgnoreCase(name)) {
                return manufacturer;
            }
        }
        return null;
    }

    // *** Mechanic Methods ***
    public void addMechanic(Mechanic mechanic) {
        if (!mechanics.contains(mechanic)) {
            mechanics.add(mechanic);
            System.out.println("Mechanic " + mechanic.getName() + " has been added to the system.");
        } else {
            System.out.println("Mechanic " + mechanic.getName() + " already exists in the system.");
        }
    }

    // *** Part Supplier Methods ***
    public void addPartSupplier(String manufacturerName, String supplierName, String contactInfo) {
        Manufacturer manufacturer = findManufacturerByName(manufacturerName);
        if (manufacturer != null) {
            PartSupplier supplier = new PartSupplier(supplierName, contactInfo, manufacturer);
            suppliers.add(supplier);
            System.out.println("Supplier " + supplierName + " added to manufacturer " + manufacturerName);
        } else {
            System.out.println("Manufacturer not found.");
        }
    }

 // *** Customer Methods ***
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer " + customer.getName() + " has been added to the system.");
        sendRegistrationNotification(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers); // Return a copy of the customer list
    }

    public List<Customer> getRegisteredCustomers() {
        List<Customer> registeredCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isRegistered()) {
                registeredCustomers.add(customer);
            }
        }
        return registeredCustomers;
    }

    public List<Customer> getUnregisteredCustomers() {
        List<Customer> unregisteredCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (!customer.isRegistered()) {
                unregisteredCustomers.add(customer);
            }
        }
        return unregisteredCustomers;
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findUnregisteredCustomerByName(String name) {
        for (Customer customer : getUnregisteredCustomers()) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    // New: Send registration notification
    private void sendRegistrationNotification(Customer customer) {
        if (customer.isRegistered()) {
            System.out.println("Notification: Welcome, " + customer.getName() + "! You are successfully registered.");
        } else {
            System.out.println("Notification: Hello, " + customer.getName() + "! You are added as a walk-in customer. Register to avail exciting offers.");
        }
    }

    // New: Send offer notifications
    public void sendOffersToCustomers(Offer offer) {
        System.out.println("\n--- Sending Offer Notifications ---");
        for (Customer customer : customers) {
            if (customer.isRegistered()) {
                System.out.println("Notification to " + customer.getName() + ": As a registered customer, enjoy this offer: \n" + offer);
            } else {
                System.out.println("Notification to " + customer.getName() + ": Register now to enjoy this offer: \n" + offer);
            }
        }
    }

    // *** Vehicle Methods ***
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getLicensePlate() + " added to the system.");
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return vehicle;
            }
        }
        return null;
    }

 // Method for manager to add a vehicle
    public void addVehicleByManager(String licensePlate, String make, String model, String ownerName, String ownerEmail, String ownerPhoneNumber) {
        // Check if the owner is an existing customer
        Customer owner = findCustomerByName(ownerName);

        if (owner == null) {
            // Create a new customer if not found
            owner = new Customer(ownerName, ownerEmail, ownerPhoneNumber);
            addCustomer(owner);
            System.out.println("New customer " + ownerName + " has been added.");
        } else {
            System.out.println("Existing customer " + ownerName + " found.");
        }

        // Check if a vehicle with the same license plate already exists
        if (findVehicleByLicensePlate(licensePlate) != null) {
            System.out.println("A vehicle with license plate " + licensePlate + " already exists.");
            return;
        }

        // Add the vehicle to the system
        Vehicle vehicle = new Vehicle(licensePlate, make, model, owner);
        addVehicle(vehicle);
        System.out.println("Vehicle " + licensePlate + " successfully added for customer " + ownerName + ".");
    }

    public List<Vehicle> getVehiclesByCustomer(Customer customer) {
        List<Vehicle> customerVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().equals(customer)) {
                customerVehicles.add(vehicle);
            }
        }
        return customerVehicles;
    }

    // *** Task Methods ***
    public void createTaskForVehicle(Vehicle vehicle, String taskDetails) {
        Task task = new Task(taskDetails, 1, vehicle); // Default priority: 1
        tasks.add(task);
        System.out.println("Task for vehicle " + vehicle.getLicensePlate() + " added: " + taskDetails);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added to the system: " + task.getTaskDetails());
    }

    public void completeTask(Task task) {
        if (task != null && task.getStatus().equals(Task.PENDING)) {
            task.setStatus(Task.COMPLETED);
            System.out.println("Task '" + task.getTaskDetails() + "' marked as completed.");
        } else {
            System.out.println("Task is either already completed or doesn't exist.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks in the system:");
            for (Task task : tasks) {
                System.out.println("Task: " + task.getTaskDetails() + " for vehicle " +
                        (task.getVehicle() != null ? task.getVehicle().getLicensePlate() : "None"));
            }
        }
    }

    public List<Task> getPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

 // *** Payment Methods ***

    public void addPayment(Vehicle vehicle, Payment payment) {
        vehiclePayments.put(vehicle, payment);
        System.out.println("Payment for vehicle " + vehicle.getLicensePlate() + " has been added.");
    }

    public Payment getPayment(Vehicle vehicle) {
        return vehiclePayments.get(vehicle);
    }

    public void markPaymentAsPaid(Vehicle vehicle) {
        Payment payment = vehiclePayments.get(vehicle);
        if (payment != null && !payment.isPaid()) {
            payment.markAsPaid();
            System.out.println("Payment for vehicle " + vehicle.getLicensePlate() + " has been marked as paid.");
            System.out.println("Notification sent to customer: Your payment for vehicle " +
                    vehicle.getLicensePlate() + " has been successfully received. Thank you!");
        } else if (payment == null) {
            System.out.println("No payment found for vehicle " + vehicle.getLicensePlate());
        } else {
            System.out.println("Payment for vehicle " + vehicle.getLicensePlate() + " is already paid.");
        }
    }

    // New Method: Record or update a payment
    public void recordPayment(String licensePlate, double amount) {
        Vehicle vehicle = findVehicleByLicensePlate(licensePlate);
        if (vehicle != null) {
            Payment payment = vehiclePayments.get(vehicle);
            if (payment == null) {
                payment = new Payment(amount);
                vehiclePayments.put(vehicle, payment);
                System.out.println("Payment of $" + amount + " has been recorded for vehicle " + licensePlate);
                System.out.println("Notification sent to customer: Payment of $" + amount + " has been successfully recorded.");
            } else if (!payment.isPaid()) {
                payment.setAmount(amount);
                payment.markAsPaid();
                System.out.println("Payment of $" + amount + " has been updated and marked as paid for vehicle " + licensePlate);
                System.out.println("Notification sent to customer: Payment of $" + amount + " has been successfully received. Thank you!");
            } else {
                System.out.println("Payment for vehicle " + licensePlate + " is already marked as paid.");
            }
        } else {
            System.out.println("Vehicle with license plate " + licensePlate + " not found.");
        }
    }

    public List<Customer> getCustomersWithPendingPayments() {
        List<Customer> customersWithPendingPayments = new ArrayList<>();
        for (Vehicle vehicle : vehiclePayments.keySet()) {
            Payment payment = vehiclePayments.get(vehicle);
            if (payment != null && !payment.isPaid()) {
                Customer owner = vehicle.getOwner();
                if (!customersWithPendingPayments.contains(owner)) {
                    customersWithPendingPayments.add(owner);
                }
            }
        }
        return customersWithPendingPayments;
    }

}
