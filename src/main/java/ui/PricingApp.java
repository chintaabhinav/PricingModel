/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import model.Business.Business;
import model.Business.ConfigureABusiness;

public class PricingApp extends JFrame {

    private Business business;

    public PricingApp() {
        // Initialize the business model
        business = ConfigureABusiness.initialize();

        // Set up the main JFrame
        setTitle("Pricing Simulation Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add GUI components
        setupUI();
    }

    private void setupUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs for different functionalities
        tabbedPane.addTab("Browse Products", new BrowseProductsPanel(business));
        tabbedPane.addTab("Adjust Prices", new AdjustPricesPanel(business));
        tabbedPane.addTab("Run Simulation", new SimulationPanel(business));
        tabbedPane.addTab("Reports", new ReportsPanel(business));

        add(tabbedPane, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PricingApp app = new PricingApp();
            app.setVisible(true);
        });
    }
}

