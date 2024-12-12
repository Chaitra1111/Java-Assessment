package com.baymotors.model;

import com.baymotors.util.ValidationUtil;
import java.util.UUID;

public class Vehicle {
    private final String id; // Unique identifier
    private String licensePlate;
    private String make;
    private String model;
    private Customer owner;
    private String taskDetails;

    // Constructor
    public Vehicle(String licensePlate, String make, String model, Customer owner) {
        validateInputs(licensePlate, make, model, owner);

        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.owner = owner;
        this.taskDetails = null; // No task assigned initially
    }

    // Overloaded constructor with three arguments
    public Vehicle(String licensePlate, String make, Customer owner) {
        this(licensePlate, make, "Unknown", owner); // Default model as "Unknown"
    }

    // Helper method to validate inputs
    private void validateInputs(String licensePlate, String make, String model, Customer owner) {
        if (!ValidationUtil.isValidLicensePlate(licensePlate)) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
        if (!ValidationUtil.isValidModel(make)) {
            throw new IllegalArgumentException("Invalid make name.");
        }
        if (!ValidationUtil.isValidModel(model)) {
            throw new IllegalArgumentException("Invalid model name.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Vehicle must have an owner.");
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Customer getOwner() {
        return owner;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    // Setters
    public void setLicensePlate(String licensePlate) {
        if (!ValidationUtil.isValidLicensePlate(licensePlate)) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
        this.licensePlate = licensePlate;
    }

    public void setMake(String make) {
        if (!ValidationUtil.isValidModel(make)) {
            throw new IllegalArgumentException("Invalid make name.");
        }
        this.make = make;
    }

    public void setModel(String model) {
        if (!ValidationUtil.isValidModel(model)) {
            throw new IllegalArgumentException("Invalid model name.");
        }
        this.model = model;
    }

    public void setTaskDetails(String taskDetails) {
        if (taskDetails != null && taskDetails.trim().isEmpty()) {
            throw new IllegalArgumentException("Task details cannot be an empty string.");
        }
        this.taskDetails = taskDetails;
    }

    public void setOwner(Customer owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null.");
        }
        this.owner = owner;
    }

    // Transfer ownership
    public void transferOwnership(Customer newOwner) {
        if (newOwner == null) {
            throw new IllegalArgumentException("New owner cannot be null.");
        }
        this.owner = newOwner;
    }

    @Override
    public String toString() {
        return "Vehicle [ID: " + id + ", License Plate: " + licensePlate + ", Make: " + make +
               ", Model: " + model + ", Owner: " + owner.getName() + "]";
    }
}
