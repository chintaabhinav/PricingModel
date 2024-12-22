/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {

    private Market market;
    private Channel channel;

    // Constructor initializes the market and channel
    public MarketChannelAssignment(Market market, Channel channel) {
        this.market = market;
        this.channel = channel;
    }

    // Getter for market
    public Market getMarket() {
        return market;
    }

    // Setter for market
    public void setMarket(Market market) {
        this.market = market;
    }

    // Getter for channel
    public Channel getChannel() {
        return channel;
    }

    // Setter for channel
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MarketChannelAssignment{" +
                "market=" + market +
                ", channel=" + channel +
                '}';
    }
}