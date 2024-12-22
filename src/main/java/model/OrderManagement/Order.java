/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class Order {

    private ArrayList<OrderItem> orderItems;
    private CustomerProfile customer;
    private SalesPersonProfile salesperson;
    private MarketChannelAssignment marketChannelAssignment;
    private String status;

    public Order() {
        orderItems = new ArrayList<>();
        status = "in process";
    }

    public Order(CustomerProfile customerProfile) {
        this();
        this.customer = customerProfile;
        customer.addCustomerOrder(this);
        this.salesperson = null;
    }

    public Order(CustomerProfile customerProfile, SalesPersonProfile salespersonProfile) {
        this(customerProfile);
        this.salesperson = salespersonProfile;
        salesperson.addSalesOrder(this);
    }

    public OrderItem newOrderItem(Product product, int actualPrice, int quantity) {
        OrderItem orderItem = new OrderItem(product, actualPrice, quantity);
        orderItems.add(orderItem);
        return orderItem;
    }

    // Calculates the total price of the order
    public int getOrderTotal() {
        return orderItems.stream().mapToInt(OrderItem::getOrderItemTotal).sum();
    }

    // Calculates the price performance for the order
    public int getOrderPricePerformance() {
        return orderItems.stream().mapToInt(OrderItem::calculatePricePerformance).sum();
    }

    // Counts the number of order items that are above the target price
    public int getNumberOfOrderItemsAboveTarget() {
        return (int) orderItems.stream().filter(OrderItem::isActualAboveTarget).count();
    }

    // Determines if the order is above the total target
    public boolean isOrderAboveTotalTarget() {
        int targetSum = orderItems.stream().mapToInt(OrderItem::getOrderItemTargetTotal).sum();
        return getOrderTotal() > targetSum;
    }

    public void cancelOrder() {
        status = "Cancelled";
    }

    public void submit() {
        status = "Submitted";
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public SalesPersonProfile getSalesperson() {
        return salesperson;
    }
}