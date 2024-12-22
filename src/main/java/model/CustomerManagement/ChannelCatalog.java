/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import model.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {

    private ArrayList<Channel> channels; // List to store channels

    // Constructor initializes the channel list
    public ChannelCatalog() {
        channels = new ArrayList<>();
    }

    // Adds a new channel to the catalog
    public Channel addChannel(String name, String type) {
        Channel channel = new Channel(name, type);
        channels.add(channel);
        return channel;
    }

    // Finds and returns a channel by name
    public Channel findChannelByName(String name) {
        for (Channel channel : channels) {
            if (channel.getName().equalsIgnoreCase(name)) {
                return channel;
            }
        }
        return null; // Return null if no matching channel is found
    }

    // Returns the list of channels
    public ArrayList<Channel> getChannels() {
        return channels;
    }

    // Removes a channel by name
    public boolean removeChannelByName(String name) {
        return channels.removeIf(channel -> channel.getName().equalsIgnoreCase(name));
    }

    @Override
    public String toString() {
        return "ChannelCatalog{channels=" + channels + '}';
    }
}