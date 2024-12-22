/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerDirectory {

    private Business business;
    private ArrayList<CustomerProfile> customerList;

    public CustomerDirectory(Business business) {
        this.business = business;
        this.customerList = new ArrayList<>();
    }

    // Creates and adds a new customer profile
    public CustomerProfile newCustomerProfile(Person person) {
        CustomerProfile customerProfile = new CustomerProfile(person);
        customerList.add(customerProfile);
        return customerProfile;
    }

    // Finds a customer by ID
    public CustomerProfile findCustomer(String id) {
        for (CustomerProfile customerProfile : customerList) {
            if (customerProfile.isMatch(id)) {
                return customerProfile;
            }
        }
        return null; // Not found
    }

    // Generates a customer performance report
    public CustomersReport generateCustomerPerformanceReport() {
        CustomersReport customersReport = new CustomersReport();
        for (CustomerProfile customerProfile : customerList) {
            CustomerSummary customerSummary = new CustomerSummary(customerProfile);
            customersReport.addCustomerSummary(customerSummary);
        }
        return customersReport;
    }

    // Getter for the customer list
    public ArrayList<CustomerProfile> getCustomerList() {
        return customerList;
    }
}
