package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Business.Business;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.Supplier.Supplier;
import java.util.List;

public class ReportsPanel extends JPanel {

    private Business business;
    private JButton generateReportButton;

    public ReportsPanel(Business business) {
        this.business = business;
        setLayout(new BorderLayout());

        // Button to generate the detailed report
        generateReportButton = new JButton("Generate Detailed Report");
        generateReportButton.setToolTipText("Click to generate a detailed performance report for all products.");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetailedReport();
            }
        });

        // Adding button to the panel
        add(generateReportButton, BorderLayout.NORTH);
    }

    private void showDetailedReport() {
        // Prepare detailed report data
        StringBuilder report = new StringBuilder();
        report.append("Detailed Product Performance Report:\n\n");

        boolean hasData = false; // Flag to check if any data is present

        for (Supplier supplier : business.getSupplierDirectory().getSupplierList()) {
            ProductsReport productsReport = supplier.prepareProductsReport();
            List<ProductSummary> productSummaries = productsReport.getProductSummaryList(); // Assuming this method exists

            if (productSummaries == null || productSummaries.isEmpty()) {
                continue; // Skip suppliers with no data
            }

            hasData = true; // Set flag if data is found

            report.append(String.format("Supplier: %s\n", supplier.getName()));
            report.append(String.format("Total Products: %d\n", supplier.getProductCatalog().getProductList().size()));

            for (ProductSummary summary : productSummaries) {
                report.append(String.format("  Product: %s\n", summary.getSubjectProduct().toString()));

                // Optional error handling for missing data fields
                try {
                    report.append(String.format("    - Initial Revenue: %d\n", summary.getInitialRevenue())); // Assuming getInitialRevenue() exists
                    report.append(String.format("    - Current Revenue: %d\n", summary.getCurrentRevenue())); // Assuming getCurrentRevenue() exists
                } catch (NullPointerException ex) {
                    report.append("    - Revenue data unavailable\n");
                }

                report.append(String.format("    - Sales Above Target: %d\n", summary.getNumberAboveTarget()));
                report.append(String.format("    - Sales Below Target: %d\n", summary.getNumberBelowTarget()));
                report.append("\n");
            }
            report.append("\n");
        }

        if (!hasData) {
            JOptionPane.showMessageDialog(this, "No data available to generate the report.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Display report in a new window
        JFrame reportFrame = new JFrame("Detailed Product Performance Report");
        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportFrame.add(new JScrollPane(textArea));
        reportFrame.setSize(600, 500);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
    }
}
