package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import model.Business.Business;
import model.ProductManagement.Product;

public class AdjustPricesPanel extends JPanel {

    private Business business;
    private JTable productTable;
    private JTextField newPriceField;
    private JButton adjustButton;
    private JButton autoAdjustButton;
    private JLabel statusLabel;

    public AdjustPricesPanel(Business business) {
        this.business = business;

        // Set layout and background
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 240, 240));

        // Add a title
        JLabel titleLabel = new JLabel("Adjust Product Prices", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Display products in a JTable with Supplier and Customer columns
        String[] columnNames = {"Supplier Name", "Product Name", "Customer Name", "Floor Price", "Ceiling Price", "Target Price"};
        Object[][] data = business.getSupplierDirectory().getSupplierList().stream()
                .flatMap(supplier -> supplier.getProductCatalog().getProductList().stream()
                        .flatMap(product -> business.getCustomerDirectory().getCustomerList().stream()
                                .map(customer -> new Object[]{
                                        supplier.getName(),
                                        product.toString(),
                                        customer.getPerson(),
                                        product.getFloorPrice(),
                                        product.getCeilingPrice(),
                                        product.getTargetPrice()
                                })))
                .toArray(Object[][]::new);

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        productTable = new JTable(model);
        productTable.setRowHeight(30);
        productTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productTable.setGridColor(Color.LIGHT_GRAY);

        // Style table header
        JTableHeader header = productTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 16));
        header.setBackground(new Color(200, 200, 200));
        header.setForeground(Color.BLACK);

        // Alternating row colors
        productTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 200), 1));
        add(scrollPane, BorderLayout.CENTER);

        // Panel for adjusting price
        JPanel adjustPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        adjustPanel.setBackground(new Color(230, 230, 250));

        JLabel priceLabel = new JLabel("New Target Price:");
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        adjustPanel.add(priceLabel);

        newPriceField = new JTextField(10);
        newPriceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        newPriceField.setToolTipText("Enter a new target price within the valid range.");
        adjustPanel.add(newPriceField);

        adjustButton = createStyledButton("Adjust Price");
        adjustPanel.add(adjustButton);

        autoAdjustButton = createStyledButton("Auto Adjust Prices");
        adjustPanel.add(autoAdjustButton);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        statusLabel.setForeground(Color.DARK_GRAY);
        adjustPanel.add(statusLabel);

        add(adjustPanel, BorderLayout.SOUTH);

        // Add action listeners
        setupActionListeners(model);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 110, 160));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }

    private void setupActionListeners(DefaultTableModel model) {
        // Action listener for manual price adjustments
        adjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = productTable.convertRowIndexToModel(selectedRow);
                    String productName = (String) model.getValueAt(modelRow, 1);

                    Product selectedProduct = business.getSupplierDirectory().getSupplierList().stream()
                            .flatMap(supplier -> supplier.getProductCatalog().getProductList().stream())
                            .filter(product -> product.toString().equals(productName))
                            .findFirst().orElse(null);

                    if (selectedProduct != null) {
                        String newPriceText = newPriceField.getText().trim();
                        if (newPriceText.isEmpty()) {
                            JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                                    "Please enter a new target price.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            int newTargetPrice = Integer.parseInt(newPriceText);
                            if (newTargetPrice >= selectedProduct.getFloorPrice() && newTargetPrice <= selectedProduct.getCeilingPrice()) {
                                selectedProduct.updateProduct(selectedProduct.getFloorPrice(), selectedProduct.getCeilingPrice(), newTargetPrice);
                                model.setValueAt(newTargetPrice, modelRow, 5); // Update display
                                JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                                        "Price adjusted successfully for product: " + productName,
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                statusLabel.setText("Price adjusted for: " + productName);
                            } else {
                                JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                                        "Invalid price: must be between floor and ceiling prices.",
                                        "Invalid Input",
                                        JOptionPane.WARNING_MESSAGE);
                                statusLabel.setText("Invalid price entry.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                                    "Please enter a valid number.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            statusLabel.setText("Invalid number format.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                                "Product not found.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        statusLabel.setText("Product not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                            "Please select a product to adjust.",
                            "Selection Error",
                            JOptionPane.WARNING_MESSAGE);
                    statusLabel.setText("No product selected.");
                }
            }
        });

        // Action listener for auto-adjust prices
        autoAdjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business.adjustPricesBasedOnPerformance(); // Call to business logic for auto adjustment
                JOptionPane.showMessageDialog(AdjustPricesPanel.this,
                        "Prices have been automatically adjusted based on sales performance.",
                        "Auto Adjust Complete",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTableData(model);
                statusLabel.setText("Auto adjustment completed.");
            }
        });

        // Reset status label when a new product is selected
        productTable.getSelectionModel().addListSelectionListener(e -> statusLabel.setText(" "));
    }

    private void refreshTableData(DefaultTableModel model) {
        Object[][] updatedData = business.getSupplierDirectory().getSupplierList().stream()
                .flatMap(supplier -> supplier.getProductCatalog().getProductList().stream()
                        .flatMap(product -> business.getCustomerDirectory().getCustomerList().stream()
                                .map(customer -> new Object[]{
                                        supplier.getName(),
                                        product.toString(),
                                        customer.getPerson(),
                                        product.getFloorPrice(),
                                        product.getCeilingPrice(),
                                        product.getTargetPrice()
                                })))
                .toArray(Object[][]::new);

        model.setDataVector(updatedData, new String[]{"Supplier Name", "Product Name", "Customer Name", "Floor Price", "Ceiling Price", "Target Price"});
    }
}
