/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import model.Business.Business;
import model.Business.ConfigureABusiness;

/**
 *
 * @author kal bugrara
 */
public class PricingModel {

    public static void main(String[] args) {
        // Initialize business data
        Business business = ConfigureABusiness.initialize();

        // Create the main frame
        JFrame frame = new JFrame("Pricing Model Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set desired size
        frame.setLocationRelativeTo(null);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Browse Products", new BrowseProductsPanel(business));
        tabbedPane.addTab("Adjust Prices", new AdjustPricesPanel(business));
        tabbedPane.addTab("Run Simulation", new SimulationPanel(business));
        tabbedPane.addTab("Reports", new ReportsPanel(business));

        // Add the tabbed pane to the frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}