/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class SupplierDirectory {

    private ArrayList<Supplier> suppliers;

    // Constructor initializes the list of suppliers
    public SupplierDirectory() {
        this.suppliers = new ArrayList<>();
    }

    // Creates and adds a new supplier with the specified name
    public Supplier newSupplier(String name) {
        Supplier supplier = new Supplier(name);
        suppliers.add(supplier);
        return supplier;
    }

    // Finds and returns a supplier by name
    public Supplier findSupplier(String name) {
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }
        return null; // Not found
    }

    // Returns the list of suppliers
    public ArrayList<Supplier> getSupplierList() {
        return suppliers;
    }
}