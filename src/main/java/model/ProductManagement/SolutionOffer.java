/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {

    private ArrayList<Product> products;
    private int price; // Represents the target price for the solution offer
    private MarketChannelAssignment marketChannelCombination;

    // Constructor initializes the market channel combination and products list
    public SolutionOffer(MarketChannelAssignment marketChannelCombination) {
        this.marketChannelCombination = marketChannelCombination;
        this.products = new ArrayList<>();
    }

    // Adds a product to the solution offer
    public void addProduct(Product product) {
        products.add(product);
    }

    // Sets the price of the solution offer
    public void setPrice(int price) {
        this.price = price;
    }

    // Getter for the price
    public int getPrice() {
        return price;
    }

    // Getter for the list of products
    public ArrayList<Product> getProducts() {
        return products;
    }

    // Getter for the market channel combination
    public MarketChannelAssignment getMarketChannelCombination() {
        return marketChannelCombination;
    }
}
