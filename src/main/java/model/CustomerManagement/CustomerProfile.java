/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.MarketModel.Market;
import model.OrderManagement.Order;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerProfile {

    private ArrayList<Order> orders;
    private ArrayList<Market> markets;
    private Person person;

    public CustomerProfile(Person person) {
        this.person = person;
        this.orders = new ArrayList<>();
        this.markets = new ArrayList<>();
    }

    // Calculates the total price performance across all orders
    public int getTotalPricePerformance() {
        int totalPerformance = 0;
        for (Order order : orders) {
            totalPerformance += order.getOrderPricePerformance();
        }
        return totalPerformance;
    }

    // Counts the number of orders above the total target
    public int getNumberOfOrdersAboveTotalTarget() {
        int count = 0;
        for (Order order : orders) {
            if (order.isOrderAboveTotalTarget()) {
                count++;
            }
        }
        return count;
    }

    // Counts the number of orders below the total target
    public int getNumberOfOrdersBelowTotalTarget() {
        int count = 0;
        for (Order order : orders) {
            if (!order.isOrderAboveTotalTarget()) {
                count++;
            }
        }
        return count;
    }

    // Checks if the customer matches the given ID
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    // Adds an order to the customer's profile
    public void addCustomerOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return person.getPersonId();
    }

    public String getCustomerId() {
        return person.getPersonId();
    }

    public Person getPerson() {
        return person;
    }
}