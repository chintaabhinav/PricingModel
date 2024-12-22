
package model.OrderManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class MasterOrderReport {

    private ArrayList<OrderSummary> orderSummaryList;

    // Constructor initializes the order summary list
    public MasterOrderReport() {
        this.orderSummaryList = new ArrayList<>();
    }

    // Generates an order report based on a list of orders
    public void generateOrderReport(ArrayList<Order> orders) {
        for (Order order : orders) {
            OrderSummary orderSummary = new OrderSummary(order);
            orderSummaryList.add(orderSummary);
        }
    }

    // Finds and returns the most profitable order summary
    public OrderSummary getTopProfitableOrder() {
        OrderSummary topOrder = null;
        for (OrderSummary orderSummary : orderSummaryList) {
            if (topOrder == null || orderSummary.getOrderProfit() > topOrder.getOrderProfit()) {
                topOrder = orderSummary;
            }
        }
        return topOrder;
    }

    // Getter for the order summary list
    public ArrayList<OrderSummary> getOrderSummaryList() {
        return orderSummaryList;
    }
}

