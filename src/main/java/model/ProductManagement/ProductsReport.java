/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class ProductsReport {

    private ArrayList<ProductSummary> productSummaryList;

    public ProductsReport() {
        this.productSummaryList = new ArrayList<>();
    }

    // Adds a product summary to the report
    public void addProductSummary(ProductSummary productSummary) {
        productSummaryList.add(productSummary);
    }

    // Retrieves the list of product summaries
    public ArrayList<ProductSummary> getProductSummaryList() {
        return productSummaryList;
    }

    // Finds the product with the most sales above target
    public ProductSummary getTopProductAboveTarget() {
        ProductSummary topProduct = null;
        for (ProductSummary productSummary : productSummaryList) {
            if (topProduct == null || productSummary.getNumberAboveTarget() > topProduct.getNumberAboveTarget()) {
                topProduct = productSummary;
            }
        }
        return topProduct;
    }

    // Retrieves a list of products always above target
    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {
        ArrayList<ProductSummary> productsAlwaysAboveTarget = new ArrayList<>();
        for (ProductSummary productSummary : productSummaryList) {
            if (productSummary.isProductAlwaysAboveTarget()) {
                productsAlwaysAboveTarget.add(productSummary);
            }
        }
        return productsAlwaysAboveTarget;
    }
}