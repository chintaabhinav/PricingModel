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
public class Channel {

    private String name;
    private String type; // Example: communication, sales, marketing
    private boolean isActive;

    // Default constructor
    public Channel() {
        this.name = "Unnamed";
        this.type = "Unknown";
        this.isActive = true;
    }

    // Constructor with parameters
    public Channel(String name, String type) {
        this.name = name;
        this.type = type;
        this.isActive = true;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Setter for type
    public void setType(String type) {
        this.type = type;
    }

    // Getter for active status
    public boolean isActive() {
        return isActive;
    }

    // Setter for active status
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Channel{name='" + name + "', type='" + type + "', isActive=" + isActive + '}';
    }
}