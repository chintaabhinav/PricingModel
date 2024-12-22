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
public class SolutionOfferCatalog {

    private ArrayList<SolutionOffer> solutionOffers;

    // Constructor initializes the solution offers list
    public SolutionOfferCatalog() {
        this.solutionOffers = new ArrayList<>();
    }

    // Adds a new solution offer to the catalog
    public void addSolutionOffer(SolutionOffer solutionOffer) {
        solutionOffers.add(solutionOffer);
    }

    // Returns the list of solution offers
    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }

    // Finds a solution offer by market channel assignment (simple example)
    public SolutionOffer findSolutionOfferByMarketChannel(MarketChannelAssignment marketChannel) {
        for (SolutionOffer solutionOffer : solutionOffers) {
            if (solutionOffer.getMarketChannelCombination().equals(marketChannel)) {
                return solutionOffer;
            }
        }
        return null; // Return null if no matching solution offer is found
    }
}