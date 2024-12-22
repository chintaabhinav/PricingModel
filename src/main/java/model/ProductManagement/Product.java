/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */
public class Product {

    private String name;
    private int floorPrice;
    private int ceilingPrice;
    private int targetPrice;
    private ArrayList<OrderItem> orderItems;

    // Constructor without a name
    public Product(int floorPrice, int ceilingPrice, int targetPrice) {
        this.floorPrice = floorPrice;
        this.ceilingPrice = ceilingPrice;
        this.targetPrice = targetPrice;
        this.orderItems = new ArrayList<>();
    }

    // Constructor with a name
    public Product(String name, int floorPrice, int ceilingPrice, int targetPrice) {
        this.name = name;
        this.floorPrice = floorPrice;
        this.ceilingPrice = ceilingPrice;
        this.targetPrice = targetPrice;
        this.orderItems = new ArrayList<>();
    }

    // Updates the product's pricing information
    public Product updateProduct(int floorPrice, int ceilingPrice, int targetPrice) {
        this.floorPrice = floorPrice;
        this.ceilingPrice = ceilingPrice;
        this.targetPrice = targetPrice;
        return this;
    }

    // Getter for target price
    public int getTargetPrice() {
        return targetPrice;
    }

    // Adds an order item associated with the product
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    // Counts the number of sales above the target price
    public int getNumberOfProductSalesAboveTarget() {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isActualAboveTarget()) {
                count++;
            }
        }
        return count;
    }

    // Counts the number of sales below the target price
    public int getNumberOfProductSalesBelowTarget() {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isActualBelowTarget()) {
                count++;
            }
        }
        return count;
    }

    // Checks if the product is always above target in terms of sales
    public boolean isProductAlwaysAboveTarget() {
        for (OrderItem orderItem : orderItems) {
            if (!orderItem.isActualAboveTarget()) {
                return false;
            }
        }
        return true;
    }

    // Calculates the order price performance
    public int getOrderPricePerformance() {
        int totalPerformance = 0;
        for (OrderItem orderItem : orderItems) {
            totalPerformance += orderItem.calculatePricePerformance();
        }
        return totalPerformance;
    }

    // Calculates the total sales volume
    public int getSalesVolume() {
        int totalSales = 0;
        for (OrderItem orderItem : orderItems) {
            totalSales += orderItem.getOrderItemTotal();
        }
        return totalSales;
    }

    // Sets the product name
    public void setName(String name) {
        this.name = name;
    }

    // Returns the product name
    @Override
    public String toString() {
        return name;
    }

    // Getters for floor and ceiling prices
    public int getFloorPrice() {
        return floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }
}
