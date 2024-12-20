package com.baymotors.model;

import java.util.ArrayList;
import java.util.List;

import com.baymotors.util.ValidationUtil;

public class Mechanic extends User {

    private List<Task> tasks; // List of tasks assigned to the mechanic

    // Constructor
    public Mechanic(String name, String email, String username, String password) {
        super(name, email, username, password);
        if (!ValidationUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.tasks = new ArrayList<>();
    }

    // Method to view tasks assigned to the mechanic
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks assigned.");
            return;
        }
        System.out.println("Tasks assigned to " + getName() + ":");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    // Method to add a task to the mechanic's list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Overloaded Method to complete a task using task details (String)
    public void completeTask(String taskDetails) {
        Task taskToComplete = null;
        for (Task task : tasks) {
            if (task.getTaskDetails().equalsIgnoreCase(taskDetails) && task.getStatus().equals(Task.PENDING)) {
                taskToComplete = task;
                break;
            }
        }

        if (taskToComplete != null) {
            markTaskAsCompleted(taskToComplete);
        } else {
            System.out.println("Task with details '" + taskDetails + "' not found or already completed.");
        }
    }

    // Overloaded Method to complete a task using task ID (int)
    public void completeTask(int taskId) {
        Task taskToComplete = null;
        for (Task task : tasks) {
            if (task.getTaskId() == taskId && task.getStatus().equals(Task.PENDING)) {
                taskToComplete = task;
                break;
            }
        }

        if (taskToComplete != null) {
            markTaskAsCompleted(taskToComplete);
        } else {
            System.out.println("Task with ID " + taskId + " not found or already completed.");
        }
    }

    // Helper method to mark a task as completed and notify the customer
    private void markTaskAsCompleted(Task taskToComplete) {
        taskToComplete.setStatus(Task.COMPLETED); // Mark task as completed
        System.out.println("Task '" + taskToComplete.getTaskDetails() + "' marked as completed.");

        // Notify the customer
        Vehicle vehicle = taskToComplete.getVehicle();
        if (vehicle != null) {
            System.out.println("Vehicle associated with the task: " + vehicle.getLicensePlate());
            Customer owner = vehicle.getOwner();
            if (owner != null) {
                System.out.println("Customer associated with the vehicle: " + owner.getName());
                if (owner.isRegistered()) {
                    // Simulate notification display
                    System.out.println("Notification: Dear " + owner.getName() + ", your vehicle is ready for pickup.");
                } else {
                    System.out.println("Customer " + owner.getName() + " is not registered.");
                }
            } else {
                System.out.println("No customer associated with the vehicle.");
            }
        } else {
            System.out.println("No vehicle associated with the task.");
        }

        // Optionally remove the task from the list of pending tasks
        tasks.remove(taskToComplete);
    }


    // Getter for tasks
    public List<Task> getTasks() {
        return tasks;
    }

    // Setter for tasks
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Method to print mechanic's details (optional for debugging)
    @Override
    public String getName() {
        return super.getName(); // Accessing the name from the User superclass
    }

    // Equals and HashCode for comparison based on username (or unique attribute)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mechanic mechanic = (Mechanic) obj;
        return getUsername().equals(mechanic.getUsername()); // Assuming username is unique
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode(); // Assuming username is unique
    }
}
