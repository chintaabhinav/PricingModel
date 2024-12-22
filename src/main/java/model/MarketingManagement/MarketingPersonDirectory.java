/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketingManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class MarketingPersonDirectory {

    private Business business;
    private ArrayList<MarketingPersonProfile> marketingPersonList;

    public MarketingPersonDirectory(Business business) {
        this.business = business;
        this.marketingPersonList = new ArrayList<>();
    }

    // Creates and adds a new MarketingPersonProfile
    public MarketingPersonProfile newMarketingPersonProfile(Person person) {
        MarketingPersonProfile marketingPersonProfile = new MarketingPersonProfile(person);
        marketingPersonList.add(marketingPersonProfile);
        return marketingPersonProfile;
    }

    // Finds a MarketingPersonProfile by ID
    public MarketingPersonProfile findMarketingPerson(String id) {
        for (MarketingPersonProfile marketingPersonProfile : marketingPersonList) {
            if (marketingPersonProfile.isMatch(id)) {
                return marketingPersonProfile;
            }
        }
        return null; // Not found
    }

    // Getter for the list of marketing persons
    public ArrayList<MarketingPersonProfile> getMarketingPersonList() {
        return marketingPersonList;
    }
}
