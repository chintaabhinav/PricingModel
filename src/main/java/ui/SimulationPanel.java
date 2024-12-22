package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Business.Business;
import model.CustomerManagement.CustomerProfile;
import model.ProductManagement.Product;
import model.Supplier.Supplier;

public class SimulationPanel extends JPanel {

    private Business business;
    private JButton runSimulationButton;
    private JButton maximizeProfitButton;
    private JTextArea resultsArea;
    private JTextField percentageField;

    public SimulationPanel(Business business) {
        this.business = business;

        // Set layout and background
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 240, 240));

        // Create title
        JLabel title = new JLabel("Simulation Panel", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 150));
        add(title, BorderLayout.NORTH);

        // Panel for user input
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        inputPanel.setBackground(new Color(230, 230, 250));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel percentageLabel = new JLabel("Percentage Change:");
        percentageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inputPanel.add(percentageLabel);

        percentageField = new JTextField(8);
        percentageField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        percentageField.setToolTipText("Enter percentage change (e.g., 10 for 10%).");
        inputPanel.add(percentageField);

        runSimulationButton = new JButton("Run Simulation");
        styleButton(runSimulationButton);
        runSimulationButton.setToolTipText("Click to run the simulation.");
        inputPanel.add(runSimulationButton);

        add(inputPanel, BorderLayout.NORTH);

        // Text area to display results
        resultsArea = new JTextArea(15, 50);
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        resultsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Simulation Results"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel for actions
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        actionPanel.setBackground(new Color(230, 230, 250));

        maximizeProfitButton = new JButton("Maximize Profit");
        styleButton(maximizeProfitButton);
        maximizeProfitButton.setToolTipText("Click to maximize profit.");
        actionPanel.add(maximizeProfitButton);

        add(actionPanel, BorderLayout.SOUTH);

        // Action listeners
        setupActionListeners();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 110, 160)); // Darker blue on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Original color
            }
        });
    }

    private void setupActionListeners() {
        // Run simulation button action
        runSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = percentageField.getText().trim();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(SimulationPanel.this,
                            "Please enter a percentage change.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double percentageChange;
                try {
                    percentageChange = Double.parseDouble(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SimulationPanel.this,
                            "Invalid input. Please enter a valid number.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StringBuilder results = new StringBuilder();
                results.append("Simulation Results:\n");
                results.append(String.format("Applied Percentage Change: %.2f%%\n\n", percentageChange));

                for (Supplier supplier : business.getSupplierDirectory().getSupplierList()) {
                    for (Product product : supplier.getProductCatalog().getProductList()) {
                        for (CustomerProfile customer : business.getCustomerDirectory().getCustomerList()) {
                            int originalTargetPrice = product.getTargetPrice();
                            int newTargetPrice = (int) (originalTargetPrice * (1 + percentageChange / 100));
                            product.updateProduct(product.getFloorPrice(), product.getCeilingPrice(), newTargetPrice);

                            results.append(String.format("Supplier: %s, Customer: %s, Product: %s\n",
                                    supplier.getName(),
                                    customer.getPerson(),
                                    product.toString()));
                            results.append(String.format("Old Target Price: %d, New Target Price: %d\n",
                                    originalTargetPrice, newTargetPrice));
                            results.append("---------------------------------------------\n");
                        }
                    }
                }

                JOptionPane.showMessageDialog(SimulationPanel.this,
                        "Simulation completed successfully.",
                        "Simulation Complete",
                        JOptionPane.INFORMATION_MESSAGE);
                resultsArea.setText(results.toString());
            }
        });

        // Maximize profit button action
        maximizeProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business.runProfitMaximizationSimulation();
                StringBuilder results = new StringBuilder();
                results.append("Profit Maximization Results:\n");

                for (Supplier supplier : business.getSupplierDirectory().getSupplierList()) {
                    for (Product product : supplier.getProductCatalog().getProductList()) {
                        for (CustomerProfile customer : business.getCustomerDirectory().getCustomerList()) {
                            results.append(String.format("Supplier: %s, Customer: %s, Product: %s\n",
                                    supplier.getName(),
                                    customer.getPerson(),
                                    product.toString()));
                            results.append(String.format("Final Target Price: %d\n", product.getTargetPrice()));
                            results.append("---------------------------------------------\n");
                        }
                    }
                }

                JOptionPane.showMessageDialog(SimulationPanel.this,
                        "Profit maximization completed.",
                        "Maximization Complete",
                        JOptionPane.INFORMATION_MESSAGE);
                resultsArea.setText(results.toString());
            }
        });
    }
}
