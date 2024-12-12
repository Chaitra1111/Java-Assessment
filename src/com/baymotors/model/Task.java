package com.baymotors.model;

public class Task {  
    private int taskId; // Unique task ID  
    private String taskDetails;  
    private int priority; // Priority of the task  
    private String status; // Status of the task (e.g., "Pending", "Completed")  
    private Vehicle vehicle; // Associated vehicle for the task  

    // Predefined statuses as constants  
    public static final String PENDING = "Pending";  
    public static final String COMPLETED = "Completed";  

    // Constructor with task ID, task details, priority, and vehicle  
    public Task(int taskId, String taskDetails, int priority, Vehicle vehicle) {  
        this.taskId = taskId;  
        this.taskDetails = taskDetails;  
        setPriority(priority); // Use setter to ensure validation  
        this.status = Task.PENDING; // Default status is "Pending"  
        this.vehicle = vehicle;  
    }  

    // Constructor with task details, priority, and vehicle (auto-generate task ID)  
    public Task(String taskDetails, int priority, Vehicle vehicle) {  
        this.taskId = generateTaskId(); // Auto-generate task ID  
        this.taskDetails = taskDetails;  
        setPriority(priority); // Use setter to ensure validation  
        this.status = Task.PENDING; // Default status is "Pending"  
        this.vehicle = vehicle;  
    }  

    // Default constructor (auto-generate task ID)  
    public Task() {  
        this.taskId = generateTaskId(); // Auto-generate task ID  
        this.status = Task.PENDING; // Default status is "Pending"  
    }  

    // Method to auto-generate a unique task ID  
    private int generateTaskId() {  
        return (int) (Math.random() * 10000);  
    }  

    // Getters and Setters  
    public int getTaskId() {  
        return taskId;  
    }  

    public void setTaskId(int taskId) {  
        this.taskId = taskId;  
    }  

    public String getTaskDetails() {  
        return taskDetails;  
    }  

    public void setTaskDetails(String taskDetails) {  
        this.taskDetails = taskDetails;  
    }  

    public int getPriority() {  
        return priority;  
    }  

    public void setPriority(int priority) {  
        if (priority < 1 || priority > 3) {  
            throw new IllegalArgumentException("Priority must be between 1 (High) and 3 (Low).");  
        }  
        this.priority = priority;  
    }  

    public String getStatus() {  
        return status;  
    }  

    public void setStatus(String status) {  
        if (!status.equals(Task.PENDING) && !status.equals(Task.COMPLETED)) {  
            throw new IllegalArgumentException("Invalid status. Allowed values: 'Pending', 'Completed'.");  
        }  
        this.status = status;  
    }  

    public Vehicle getVehicle() {  
        return vehicle;  
    }  

    public void setVehicle(Vehicle vehicle) {  
        this.vehicle = vehicle;  
    }  

    // Mark the task as completed  
    public void markAsCompleted() {  
        this.status = Task.COMPLETED;  
    }  

    // Check if the task is completed  
    public boolean isCompleted() {  
        return Task.COMPLETED.equals(this.status);  
    }  

    @Override  
    public String toString() {  
        return "Task ID: " + taskId + ", Task: " + taskDetails + ", Priority: " + priority + ", Status: " + status + ", Vehicle: " + (vehicle != null ? vehicle.getLicensePlate() : "None");  
    }  
}