/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.SalesManagement;

import java.util.ArrayList;

import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.Profile;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonProfile extends Profile {

    private ArrayList<Order> salesOrders;

    // Constructor initializes the profile with a person
    public SalesPersonProfile(Person person) {
        super(person);
        this.salesOrders = new ArrayList<>();
    }

    // Adds a sales order to the profile
    public void addSalesOrder(Order order) {
        salesOrders.add(order);
    }

    // Getter for the sales orders list
    public ArrayList<Order> getSalesOrders() {
        return salesOrders;
    }

    @Override
    public String getRole() {
        return "Sales";
    }
}