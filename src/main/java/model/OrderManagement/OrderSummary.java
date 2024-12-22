/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

/**
 *
 * @author kal bugrara
 */
public class OrderSummary {

    private int salesVolume;
    private boolean totalAboveTarget;
    private int orderPricePerformance;
    private int numberOfOrderItemsAboveTarget;

    public OrderSummary(Order order) {
        this.salesVolume = order.getOrderTotal();
        this.totalAboveTarget = order.isOrderAboveTotalTarget();
        this.orderPricePerformance = order.getOrderPricePerformance();
        this.numberOfOrderItemsAboveTarget = order.getNumberOfOrderItemsAboveTarget();
    }

    public int getOrderProfit() {
        return orderPricePerformance;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public boolean isTotalAboveTarget() {
        return totalAboveTarget;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        return numberOfOrderItemsAboveTarget;
    }
}