package com;

import java.util.Scanner;

import com.inventory.*;
import com.seller.*;
import com.customer.*;
import com.sales.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        shopMenu(sc);
    }

    private static void shopMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\nShop Menu:");
            System.out.println("1. Seller");
            System.out.println("2. Customer");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    sellerMenu(sc);
                    break;
                case 2:
                    customerMenu(sc);
                    break;
                case 0:
                    System.out.println("Exiting Shop Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void sellerMenu(Scanner sc) {
        int choice;
        String productName;
        String productID;
        Double productCost;
        int productQuantity;

        do {
            System.out.println("\nSeller Menu:");
            System.out.println("1. View Product List");
            System.out.println("2. Add New Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Modify Existing Product");
            System.out.println("5. View Purchase History");
            System.out.println("6. View Customer Information");
            System.out.println("0. Previous Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nView Product List");
                    ViewData.print();
                    break;
                case 2:
                    System.out.println("\nEnter Product Info");
                    System.out.print("Name: ");
                    productName = sc.nextLine();
                    System.out.print("Cost: ");
                    productCost = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Initial Quantity: ");
                    productQuantity = sc.nextInt();
                    sc.nextLine();
                    UploadProductData.addProduct(productName, productCost, productQuantity);
                    break;
                case 3:
                    System.out.println("\nRemove a Product from List");
                    System.out.print("Enter Product ID: ");
                    productID = sc.nextLine();
                    ModifyProductData.removeProduct(productID);
                    break;
                case 4:
                    System.out.println("\nModify Existing Product");
                    modifyExistingProductMenu(sc);
                    break;
                case 5:
                    System.out.println("\nView Purchase History");
                    ViewSalesData.printPurchase();
                    break;
                case 6:
                    System.out.println("\nView Customer Information");
                    ViewCustomerData.printAllCustomerInfo();
                    break;
                case 0:
                    System.out.println("\nExiting Seller Menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void customerMenu(Scanner sc) {
        int choice;
        String customerName;
        Double customerBalance;
        do {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Profile Menu");
            System.out.println("2. Create Profile");
            System.out.println("3. View Product List");
            System.out.println("4. Buy Product");
            System.out.println("0. Previous Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewProfileMenu(sc);
                    break;
                case 2:
                    System.out.println("\nEnter Customer Info");
                    System.out.print("Name: ");
                    customerName = sc.nextLine();
                    System.out.print("Initial Balance: ");
                    customerBalance = sc.nextDouble();
                    sc.nextLine();
                    new Login(customerName, customerBalance);
                    break;
                case 3:
                    System.out.println("\nView Product List");
                    ViewData.print();
                    break;
                case 4:
                    System.out.println("\nBuy Product");
                    buyProduct(sc);
                    break;
                case 0:
                    System.out.println("\nExiting Customer Menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void modifyExistingProductMenu(Scanner sc) {
        int choice;
        String productID;
        int newQuantity, currentQuantity;
        Double newCost, currentCost;

        System.out.print("Enter Product ID: ");
        productID = sc.nextLine();

        do {
            System.out.println("\nProduct Menu:");
            System.out.println("1. Modify Quantity");
            System.out.println("2. Modify Cost");
            System.out.println("0. Previoud Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nModify Product Quantity");
                    currentQuantity = RetriveData.getQuantity(productID);
                    System.out.println("Current Qunatity: " + currentQuantity);
                    System.out.print("Enter New Quantity: ");
                    newQuantity = sc.nextInt();
                    sc.nextLine();
                    ModifyProductData.modifyQuantity(productID, newQuantity);                   
                    break;
                case 2:
                    System.out.println("\nModify Product Cost");
                    currentCost = RetriveData.getCost(productID);
                    System.err.println("Current Cost: " + currentCost);
                    System.out.print("Enter New Cost: ");
                    newCost = sc.nextDouble();
                    sc.nextLine();
                    ModifyProductData.modifyCost(productID, newCost);            
                    break;
                case 0:
                    System.out.println("\nExiting Product Menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void viewProfileMenu(Scanner sc) {
        int choice;
        String customerName;
        String customerId;
        Double credit;

        System.out.println("\nCustomer Profile Menu");
        System.out.print("Enter Customer Name: ");
        customerName = sc.nextLine();
        if(!RetriveCustomerData.isAvailableName(customerName)) {
            System.out.println("Customer does not exist");
            return;
        }

        customerId = RetriveCustomerData.getCustomerId(customerName);
        ViewCustomerData.printCustomerProfile(customerId);

        do {
            System.out.println("\n1. Add Balance");
            System.out.println("2. View Purchase History");
            System.out.println("3. View Profile");
            System.out.println("0. Previous Menu");
            System.out.print("\nEnter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Add Balance");
                    System.out.print("Enter Credit Amount: ");
                    credit = sc.nextDouble();
                    sc.nextLine();
                    ModifyCustomerData.addBalance(customerId, credit);
                    break;
                case 2:
                    System.out.println("Purchase History");
                    ViewSalesData.printCustomerPurchase(customerId);
                    break;
                case 3:
                    System.out.println("Profile");
                    ViewCustomerData.printCustomerProfile(customerId);
                    break;
                case 0:
                    System.out.println("\nExiting Product Menu...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number from the menu.");
            }
        } while(choice != 0);
    }

    private static void buyProduct(Scanner sc) {
        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();
        if(!RetriveCustomerData.isAvailableName(customerName)) {
            System.out.println("customer does not exist");
            System.out.println("create a profile first");
            return;
        }
        String customerId = RetriveCustomerData.getCustomerId(customerName);
        Double customerBalance = RetriveCustomerData.getBalance(customerId);
        
        System.out.print("Enter Product ID: ");
        String productID = sc.nextLine();
        if(!RetriveData.isAvailable(productID)) {
            System.out.println("Product does not exist");
            return;
        }
        int availableQuantity = RetriveData.getQuantity(productID);
        Double costPerItem = RetriveData.getCost(productID);    
        System.out.println("Available quantity: " + availableQuantity);
        System.out.println("Cost per item: " + costPerItem);

        System.out.print("Enter Required Quantity: ");
        int requiredQuantity = sc.nextInt();
        sc.nextLine();

        while (requiredQuantity <= 0) {
            System.out.println("Required quantity has to be positive");
            System.out.print("\nEnter Required Quantity: ");
            requiredQuantity = sc.nextInt();
            sc.nextLine();
        }
        
        if (requiredQuantity > availableQuantity) {
            System.out.println("\nNot enough quantity left.");
            System.out.println("Required Quantity: " + requiredQuantity);
            System.out.println("Available Quantity: " + availableQuantity);

            System.out.println("\n1. Continue with the Purchase, Buy the available quantity");
            System.out.println("2. Cancel");
            int choice1 = sc.nextInt();
            sc.nextLine();
            if(choice1 == 2) {
                System.out.println("purchase canceled");
                return;
            }
            requiredQuantity = availableQuantity;
        }

        Double bill = requiredQuantity * costPerItem;
        System.out.println("Total Bill: " + bill);

        do {
            System.out.print("Confirm Purchase(y/n): ");
            String conformation = sc.nextLine();
            if (!conformation.equalsIgnoreCase("y")) {
                break;
            }
        } while(true);
        
        if (customerBalance < bill) {
            System.out.println("insufficient balance \npurchase canceled");
            UploadSalesData.addPurchase(customerId, productID, "Failed", requiredQuantity, bill);
            ModifyCustomerData.incrementFailedPurchases(customerId);
            return;
        }

        ModifyCustomerData.removeBalance(customerId, bill);
        ModifyCustomerData.incrementSuccessfulPurchases(customerId);
        ModifyCustomerData.increaseSpendings(customerId, bill);
        ModifyProductData.removeItems(productID, requiredQuantity);
        UploadSalesData.addPurchase(customerId, productID, "Passed", requiredQuantity, bill);
        System.out.println("Purchase Successful");
    }
}
