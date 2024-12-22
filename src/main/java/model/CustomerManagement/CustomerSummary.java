/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import model.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class CustomerSummary {

    private int orderTotal;
    private boolean isAboveTarget;
    private int orderPricePerformance;

    public CustomerSummary(CustomerProfile customerProfile) {
        // This example assumes you want to summarize some information about the customer's orders.
        orderTotal = customerProfile.getTotalPricePerformance();
        isAboveTarget = customerProfile.getNumberOfOrdersAboveTotalTarget() > 0;
        orderPricePerformance = customerProfile.getTotalPricePerformance();
    }

    // Getters
    public int getOrderTotal() {
        return orderTotal;
    }

    public boolean isAboveTarget() {
        return isAboveTarget;
    }

    public int getOrderPricePerformance() {
        return orderPricePerformance;
    }
}