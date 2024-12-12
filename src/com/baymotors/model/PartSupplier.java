package com.baymotors.model;

public class PartSupplier {
    private String name;
    private String contactInfo;
    private Manufacturer manufacturer;

    // Constructor with Manufacturer association
    public PartSupplier(String name, String contactInfo, Manufacturer manufacturer) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.manufacturer = manufacturer;
        
        if (manufacturer != null) {
            manufacturer.addPartSupplier(this);  // Add this supplier to the manufacturer's list
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}