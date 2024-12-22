/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.Random;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.MarketingManagement.MarketingPersonDirectory;
import model.MarketingManagement.MarketingPersonProfile;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.EmployeeDirectory;
import model.Personnel.EmployeeProfile;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.SalesManagement.SalesPersonDirectory;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccount;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

    public static Business initialize() {
        Business business = new Business("Sample Business");

        // Generate 5 suppliers with 10 products each
        SupplierDirectory supplierDirectory = business.getSupplierDirectory();
        for (int i = 1; i <= 5; i++) {
            Supplier supplier = supplierDirectory.newSupplier("Supplier " + i);
            ProductCatalog productCatalog = supplier.getProductCatalog();
            for (int j = 1; j <= 10; j++) {
                productCatalog.newProduct("Product " + j, 50, 100, 75 + j); // Example floor, ceiling, and target prices
            }
        }

        // Generate 10 customers
        CustomerDirectory customerDirectory = business.getCustomerDirectory();
        for (int i = 1; i <= 10; i++) {
            customerDirectory.newCustomerProfile(new Person("Customer " + i));
        }

        // Generate 10 orders per customer, randomly selecting products
        MasterOrderList masterOrderList = business.getMasterOrderList();
        Random random = new Random();
        for (CustomerProfile customer : customerDirectory.getCustomerList()) {
            for (int i = 0; i < 10; i++) {
                Order order = masterOrderList.newOrder(customer);
                Supplier randomSupplier = supplierDirectory.getSupplierList().get(random.nextInt(supplierDirectory.getSupplierList().size()));
                ProductCatalog productCatalog = randomSupplier.getProductCatalog();
                Product product = productCatalog.getProductList().get(random.nextInt(productCatalog.getProductList().size()));
                order.newOrderItem(product, random.nextInt(100) + 50, random.nextInt(5) + 1); // Random actual price and quantity
            }
        }

        // Adding example employees, salespersons, and marketing persons
        EmployeeDirectory employeeDirectory = business.getEmployeeDirectory();
        employeeDirectory.newEmployeeProfile(new Person("Alice Johnson"));
        employeeDirectory.newEmployeeProfile(new Person("Bob Williams"));

        SalesPersonDirectory salesPersonDirectory = business.getSalesPersonDirectory();
        salesPersonDirectory.newSalesPersonProfile(new Person("Charlie Brown"));

        MarketingPersonDirectory marketingPersonDirectory = business.getMarketingPersonDirectory();
        marketingPersonDirectory.newMarketingPersonProfile(new Person("Dana White"));

        return business;
    }
}