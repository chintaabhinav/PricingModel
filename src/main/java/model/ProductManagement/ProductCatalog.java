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
public class ProductCatalog {

    private String type;
    private ArrayList<Product> products;

    // Constructor with specified type
    public ProductCatalog(String type) {
        this.type = type;
        this.products = new ArrayList<>();
    }

    // Default constructor with "unknown" type
    public ProductCatalog() {
        this("unknown");
    }

    // Creates and adds a new product with specified financial parameters
    public Product newProduct(int floorPrice, int ceilingPrice, int targetPrice) {
        Product product = new Product(floorPrice, ceilingPrice, targetPrice);
        products.add(product);
        return product;
    }

    // Overloaded method to create and add a new product with a name
    public Product newProduct(String name, int floorPrice, int ceilingPrice, int targetPrice) {
        Product product = new Product(name, floorPrice, ceilingPrice, targetPrice);
        products.add(product);
        return product;
    }

    // Finds and returns a product by name
    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.toString().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // Generates a performance report for the products
    public ProductsReport generateProductPerformanceReport() {
        ProductsReport productsReport = new ProductsReport();
        for (Product product : products) {
            ProductSummary productSummary = new ProductSummary(product);
            productsReport.addProductSummary(productSummary);
        }
        return productsReport;
    }

    // Getter for the list of products
    public ArrayList<Product> getProductList() {
        return products;
    }

    // Getter and setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
