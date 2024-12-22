package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import model.Business.Business;

public class BrowseProductsPanel extends JPanel {

    private Business business;
    private JTable productTable;
    private JTextField filterField;

    public BrowseProductsPanel(Business business) {
        this.business = business;

        // Set layout and background
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        // Add a title
        JLabel titleLabel = new JLabel("Browse Products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150)); // Dark blue
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Table columns
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

        // Create table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        productTable = new JTable(model);
        productTable.setRowHeight(30);
        productTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productTable.setGridColor(Color.LIGHT_GRAY);

        // Style table header
        JTableHeader header = productTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 16));
        header.setBackground(new Color(200, 200, 200)); // Light gray background
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

        // Filter Panel
        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.setBackground(new Color(230, 230, 250)); // Light lavender background
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        filterPanel.add(new JLabel("Filter Products: "), BorderLayout.WEST);

        filterField = new JTextField();
        filterField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filterField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 200), 1));
        filterField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
                productTable.setRowSorter(sorter);
                String text = filterField.getText().trim();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
        filterPanel.add(filterField, BorderLayout.CENTER);
        add(filterPanel, BorderLayout.NORTH);
    }
}
