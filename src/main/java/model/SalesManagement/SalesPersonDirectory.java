/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.SalesManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonDirectory {

    private Business business;
    private ArrayList<SalesPersonProfile> salesPersonList;

    // Constructor initializes the business and salesperson list
    public SalesPersonDirectory(Business business) {
        this.business = business;
        this.salesPersonList = new ArrayList<>();
    }

    // Creates and adds a new SalesPersonProfile
    public SalesPersonProfile newSalesPersonProfile(Person person) {
        SalesPersonProfile salesPersonProfile = new SalesPersonProfile(person);
        salesPersonList.add(salesPersonProfile);
        return salesPersonProfile;
    }

    // Finds a SalesPersonProfile by ID
    public SalesPersonProfile findSalesPerson(String id) {
        for (SalesPersonProfile salesPersonProfile : salesPersonList) {
            if (salesPersonProfile.isMatch(id)) {
                return salesPersonProfile;
            }
        }
        return null; // Not found
    }

    // Getter for the list of salespersons
    public ArrayList<SalesPersonProfile> getSalesPersonList() {
        return salesPersonList;
    }
}
