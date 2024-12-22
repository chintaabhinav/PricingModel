    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package model.ProductManagement;

    /**
     *
     * @author kal bugrara
     */

    //this class will extract summary data from the product
    public class ProductSummary {

    private Product subjectProduct;
    private int numberOfSalesAboveTarget;
    private int numberOfSalesBelowTarget;
    private int productPricePerformance;
    private int actualSalesVolume;
    private int initialRevenue; // Added for demonstration
    private int currentRevenue; // Added for demonstration

    public ProductSummary(Product product) {
        this.subjectProduct = product;
        this.numberOfSalesAboveTarget = product.getNumberOfProductSalesAboveTarget();
        this.numberOfSalesBelowTarget = product.getNumberOfProductSalesBelowTarget();
        this.productPricePerformance = product.getOrderPricePerformance();
        this.actualSalesVolume = product.getSalesVolume();

        // Example initial and current revenue calculations (adjust as necessary)
        this.initialRevenue = calculateInitialRevenue();
        this.currentRevenue = calculateCurrentRevenue();
    }

    private int calculateInitialRevenue() {
        // Placeholder logic; adjust according to your requirements
        return subjectProduct.getTargetPrice() * actualSalesVolume;
    }

    private int calculateCurrentRevenue() {
        // Placeholder logic; adjust according to your requirements
        return subjectProduct.getTargetPrice() * actualSalesVolume; // Replace with actual logic
    }

    public int getInitialRevenue() {
        return initialRevenue;
    }

    public int getCurrentRevenue() {
        return currentRevenue;
    }

    public Product getSubjectProduct() {
        return subjectProduct;
    }

    public int getNumberAboveTarget() {
        return numberOfSalesAboveTarget;
    }

    public int getNumberBelowTarget() {
        return numberOfSalesBelowTarget;
    }

    public int getProductPricePerformance() {
        return productPricePerformance;
    }

    public int getActualSalesVolume() {
        return actualSalesVolume;
    }
    
    public int getSalesRevenues() {
        return actualSalesVolume; // Adjust this logic as needed based on your requirements
    }

    public boolean isProductAlwaysAboveTarget() {
        return numberOfSalesBelowTarget == 0;
    }
}