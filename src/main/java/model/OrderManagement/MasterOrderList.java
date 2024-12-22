/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketingManagement.MarketingPersonProfile;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class MasterOrderList {

    private ArrayList<Order> orders;
    private MasterOrderReport masterOrderReport;

    // Constructor initializes the order list
    public MasterOrderList() {
        this.orders = new ArrayList<>();
    }

    // Creates and adds a new order with a customer profile
    public Order newOrder(CustomerProfile customerProfile) {
        Order order = new Order(customerProfile);
        orders.add(order);
        return order;
    }

    // Creates and adds a new order with a customer profile and a salesperson profile
    public Order newOrder(CustomerProfile customerProfile, SalesPersonProfile salesPersonProfile) {
        Order order = new Order(customerProfile, salesPersonProfile);
        orders.add(order);
        return order;
    }

    // Generates a master order report
    public MasterOrderReport generateMasterOrderReport() {
        masterOrderReport = new MasterOrderReport();
        masterOrderReport.generateOrderReport(orders);
        return masterOrderReport;
    }

    // Calculates the total sales volume across all orders
    public int getSalesVolume() {
        int sum = 0;
        for (Order order : orders) {
            sum += order.getOrderTotal();
        }
        return sum;
    }

    // Getter for the order list
    public ArrayList<Order> getOrders() {
        return orders;
    }
}