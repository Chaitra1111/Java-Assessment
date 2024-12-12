package com.baymotors.model;

public class Payment {
    private double amount;
    private boolean paid;

    // Constructor accepting the payment amount
    public Payment(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }
        this.amount = amount;
        this.paid = false; // Default to unpaid
    }

    // Constructor accepting amount and paid status
    public Payment(double amount, boolean paid) {
        if (amount < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }
        this.amount = amount;
        this.paid = paid;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Setter for amount
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }
        this.amount = amount;
    }

    // Method to check if the payment is paid
    public boolean isPaid() {
        return paid;
    }

    // Method to mark the payment as paid
    public void markAsPaid() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Payment [amount=" + amount + ", paid=" + paid + "]";
    }
}
