/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;

import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;

/**
 *
 * @author kal bugrara
 */
public class Supplier {

    private String name;
    private ProductCatalog productCatalog;
    private ProductsReport productsReport;

    // Constructor to initialize a supplier with a specified name and a default product catalog
    public Supplier(String name) {
        this.name = name;
        this.productCatalog = new ProductCatalog("software"); // Default product type
    }

    // Prepares a report for the supplier's products
    public ProductsReport prepareProductsReport() {
        productsReport = productCatalog.generateProductPerformanceReport();
        return productsReport;
    }

    // Retrieves a list of products that are always above their target
    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {
        if (productsReport == null) {
            productsReport = prepareProductsReport();
        }
        return productsReport.getProductsAlwaysAboveTarget();
    }

    // Returns the supplier's name
    public String getName() {
        return name;
    }

    // Returns the product catalog associated with this supplier
    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    @Override
    public String toString() {
        return name;
    }
}