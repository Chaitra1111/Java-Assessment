package com.baymotors.model;

public class Offer {
    private String description;
    private String discountCode;

    // Constructor
    public Offer(String description, String discountCode) {
        this.description = description;
        this.discountCode = discountCode;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    // Setters (Optional, if needed)
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public String toString() {
        return "Offer [description=" + description + ", discountCode=" + discountCode + "]";
    }
}
