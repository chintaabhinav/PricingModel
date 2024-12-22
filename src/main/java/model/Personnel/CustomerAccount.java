/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

/**
 *
 * @author kal bugrara
 */
public class CustomerAccount {

    private String accountId;
    private double balance;
    private String status; // Example statuses: "Active", "Inactive", "Suspended"

    // Default constructor
    public CustomerAccount() {
        this.accountId = "Unknown";
        this.balance = 0.0;
        this.status = "Inactive";
    }

    // Constructor with parameters
    public CustomerAccount(String accountId, double initialBalance, String status) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.status = status;
    }

    // Getter for accountId
    public String getAccountId() {
        return accountId;
    }

    // Setter for accountId
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Adds a specified amount to the balance
    public void addBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    // Deducts a specified amount from the balance
    public boolean deductBalance(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true; // Deduction successful
        }
        return false; // Deduction failed
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
