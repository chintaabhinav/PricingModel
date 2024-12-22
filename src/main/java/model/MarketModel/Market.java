/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {

    private ArrayList<SolutionOffer> solutionOffers;
    private ArrayList<MarketChannelAssignment> channels;
    private ArrayList<String> characteristics;
    private ArrayList<Market> submarkets;
    private int size;

    // Constructor initializes characteristics and other collections
    public Market(String characteristic) {
        this.characteristics = new ArrayList<>();
        this.solutionOffers = new ArrayList<>();
        this.channels = new ArrayList<>();
        this.submarkets = new ArrayList<>();
        this.size = 0; // Default size
        this.characteristics.add(characteristic);
    }

    // Adds a characteristic to the market
    public void addCharacteristic(String characteristic) {
        characteristics.add(characteristic);
    }

    // Adds a solution offer to the market
    public void addSolutionOffer(SolutionOffer solutionOffer) {
        solutionOffers.add(solutionOffer);
    }

    // Adds a channel to the market
    public void addChannel(MarketChannelAssignment channel) {
        channels.add(channel);
    }

    // Adds a submarket to this market
    public void addSubmarket(Market submarket) {
        submarkets.add(submarket);
    }

    // Getters for the lists and fields
    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }

    public ArrayList<MarketChannelAssignment> getChannels() {
        return channels;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public ArrayList<Market> getSubmarkets() {
        return submarkets;
    }

    public int getSize() {
        return size;
    }

    // Setter for size
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Market{" +
                "characteristics=" + characteristics +
                ", size=" + size +
                '}';
    }
}