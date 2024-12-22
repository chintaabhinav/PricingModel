    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package model.Business;

    import java.util.ArrayList;
    import model.CustomerManagement.ChannelCatalog;
    import model.CustomerManagement.CustomerDirectory;
    import model.CustomerManagement.MarketCatalog;
    import model.MarketingManagement.MarketingPersonDirectory;
    import model.OrderManagement.MasterOrderList;
    import model.Personnel.EmployeeDirectory;
    import model.Personnel.PersonDirectory;
    import model.ProductManagement.Product;
    import model.ProductManagement.ProductSummary;
    import model.ProductManagement.ProductsReport;
    import model.ProductManagement.SolutionOfferCatalog;
    import model.SalesManagement.SalesPersonDirectory;
    import model.Supplier.Supplier;
    import model.Supplier.SupplierDirectory;
    import model.UserAccountManagement.UserAccountDirectory;
    /**
     *
     * @author kal bugrara
     */
    public class Business {

        private String name;
        private PersonDirectory personDirectory;
        private MasterOrderList masterOrderList;
        private CustomerDirectory customerDirectory;
        private SupplierDirectory supplierDirectory;
        private MarketCatalog marketCatalog;
        private ChannelCatalog channelCatalog;
        private SolutionOfferCatalog solutionOfferCatalog;
        private EmployeeDirectory employeeDirectory;
        private SalesPersonDirectory salesPersonDirectory;
        private UserAccountDirectory userAccountDirectory;
        private MarketingPersonDirectory marketingPersonDirectory;

        public Business(String name) {
            this.name = name;
            this.masterOrderList = new MasterOrderList();
            this.supplierDirectory = new SupplierDirectory();
            this.personDirectory = new PersonDirectory();
            this.customerDirectory = new CustomerDirectory(this);
            this.salesPersonDirectory = new SalesPersonDirectory(this);
            this.userAccountDirectory = new UserAccountDirectory();
            this.marketingPersonDirectory = new MarketingPersonDirectory(this);
            this.employeeDirectory = new EmployeeDirectory(this);
        }

        public int getSalesVolume() {
            return masterOrderList.getSalesVolume();
        }

        public PersonDirectory getPersonDirectory() {
            return personDirectory;
        }

        public UserAccountDirectory getUserAccountDirectory() {
            return userAccountDirectory;
        }

        public MarketingPersonDirectory getMarketingPersonDirectory() {
            return marketingPersonDirectory;
        }

        public SupplierDirectory getSupplierDirectory() {
            return supplierDirectory;
        }

        public ProductsReport getSupplierPerformanceReport(String supplierName) {
            Supplier supplier = supplierDirectory.findSupplier(supplierName);
            return (supplier != null) ? supplier.prepareProductsReport() : null;
        }

        public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String supplierName) {
            ProductsReport productsReport = getSupplierPerformanceReport(supplierName);
            return (productsReport != null) ? productsReport.getProductsAlwaysAboveTarget() : new ArrayList<>();
        }

        public int getHowManySupplierProductsAlwaysAboveTarget(String supplierName) {
            ProductsReport productsReport = getSupplierPerformanceReport(supplierName);
            return (productsReport != null) ? productsReport.getProductsAlwaysAboveTarget().size() : 0;
        }

        public CustomerDirectory getCustomerDirectory() {
            return customerDirectory;
        }

        public SalesPersonDirectory getSalesPersonDirectory() {
            return salesPersonDirectory;
        }

        public MasterOrderList getMasterOrderList() {
            return masterOrderList;
        }

        public EmployeeDirectory getEmployeeDirectory() {
            return employeeDirectory;
        }

        // Additional methods for price adjustments and simulations
        public void adjustPricesBasedOnPerformance() {
            for (Supplier supplier : this.getSupplierDirectory().getSupplierList()) {
                for (Product product : supplier.getProductCatalog().getProductList()) {
                    int actualSales = product.getSalesVolume();
                    int targetPrice = product.getTargetPrice();

                    // Logic for adjusting prices
                    if (actualSales < targetPrice) {
                        int newTargetPrice = Math.max(product.getFloorPrice(), (int) (targetPrice * 0.9)); // Decrease by 10%
                        product.updateProduct(product.getFloorPrice(), product.getCeilingPrice(), newTargetPrice);
                    } else if (actualSales > targetPrice) {
                        int newTargetPrice = Math.min(product.getCeilingPrice(), (int) (targetPrice * 1.1)); // Increase by 10%
                        product.updateProduct(product.getFloorPrice(), product.getCeilingPrice(), newTargetPrice);
                    }
                }
            }
        }

        public void runProfitMaximizationSimulation() {
            boolean profitIncreased = true;
            int previousProfit = calculateTotalProfit();

            while (profitIncreased) {
                adjustPricesBasedOnPerformance();
                int currentProfit = calculateTotalProfit();
                profitIncreased = currentProfit > previousProfit;
                previousProfit = currentProfit;
            }
        }

        public int calculateTotalProfit() {
            int totalProfit = 0;
            for (Supplier supplier : this.getSupplierDirectory().getSupplierList()) {
                for (Product product : supplier.getProductCatalog().getProductList()) {
                    totalProfit += product.getOrderPricePerformance();
                }
            }
            return totalProfit;
        }

        // New method to generate a detailed report
        public String generateDetailedReport() {
            StringBuilder report = new StringBuilder();
            for (Supplier supplier : this.getSupplierDirectory().getSupplierList()) {
                ProductsReport productsReport = supplier.prepareProductsReport();
                report.append("Supplier: ").append(supplier.getName()).append("\n");
                for (ProductSummary summary : productsReport.getProductSummaryList()) { // Assuming getProductSummaryList() exists
                    report.append("Product: ").append(summary.getSubjectProduct().toString()) // Assuming a method exists to get product name
                          .append(", Revenue Before: ").append(summary.getInitialRevenue()) // Update if necessary
                          .append(", Revenue After: ").append(summary.getCurrentRevenue())   // Update if necessary
                          .append(", Sales Above Target: ").append(summary.getNumberAboveTarget())
                          .append(", Sales Below Target: ").append(summary.getNumberBelowTarget())
                          .append("\n");
                }
            }
            return report.toString();
        }
    }
