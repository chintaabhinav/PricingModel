/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CustomersReport {

    private ArrayList<CustomerSummary> customerSummaries;

    public CustomersReport() {
        customerSummaries = new ArrayList<>();
    }

    // Adds a customer summary to the report
    public void addCustomerSummary(CustomerSummary customerSummary) {
        customerSummaries.add(customerSummary);
    }

    // Getter for the customer summaries list
    public ArrayList<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }
}