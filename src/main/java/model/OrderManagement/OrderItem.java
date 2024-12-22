/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import model.ProductManagement.Product;

/**
 *
 * @author kal bugrara
 */
public class OrderItem {

    private Product selectedProduct;
    private int actualPrice;
    private int quantity;

    public OrderItem(Product product, int paidPrice, int quantity) {
        this.selectedProduct = product;
        this.actualPrice = paidPrice;
        this.quantity = quantity;
        product.addOrderItem(this);
    }

    public int getOrderItemTotal() {
        return actualPrice * quantity;
    }

    public int getOrderItemTargetTotal() {
        return selectedProduct.getTargetPrice() * quantity;
    }

    public int calculatePricePerformance() {
        return (actualPrice - selectedProduct.getTargetPrice()) * quantity;
    }

    public boolean isActualAboveTarget() {
        return actualPrice > selectedProduct.getTargetPrice();
    }

    public boolean isActualBelowTarget() {
        return actualPrice < selectedProduct.getTargetPrice();
    }

    public boolean isActualATTarget() {
        return actualPrice == selectedProduct.getTargetPrice();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
