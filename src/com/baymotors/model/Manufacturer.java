package com.baymotors.model;

import java.util.ArrayList;
import java.util.List;

public class Manufacturer {
    private String name;
    private List<PartSupplier> partSuppliers;

    public Manufacturer(String name) {
        this.name = name;
        this.partSuppliers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<PartSupplier> getPartSuppliers() {
        return partSuppliers;
    }

    // Add a PartSupplier to the Manufacturer
    public void addPartSupplier(PartSupplier supplier) {
        if (!partSuppliers.contains(supplier)) {
            partSuppliers.add(supplier);
            System.out.println("Supplier " + supplier.getName() + " added to Manufacturer " + name);
        } else {
            System.out.println("Supplier " + supplier.getName() + " is already associated with Manufacturer " + name);
        }
    }

    // Remove a PartSupplier from the Manufacturer
    public void removePartSupplier(PartSupplier supplier) {
        if (partSuppliers.remove(supplier)) {
            System.out.println("Supplier " + supplier.getName() + " removed from Manufacturer " + name);
        } else {
            System.out.println("Supplier " + supplier.getName() + " not found for Manufacturer " + name);
        }
    }

    // Override toString method to display Manufacturer details
    @Override
    public String toString() {
        return "Manufacturer{name='" + name + "', partSuppliers=" + partSuppliers + "}";
    }
}