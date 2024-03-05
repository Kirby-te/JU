package com;

import java.util.Scanner;
import com.inventory.*;
import com.seller.*;
import com.customer.*;

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
        String productInfo;
        Double productCost;
        int productQuantity;
        do {
            System.out.println("\nSeller Menu:");
            System.out.println("1. View Product List");
            System.out.println("2. Add Product");
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
                    System.out.println("View Product List");
                    
                    break;
                case 2:
                    System.out.println("Add New Product");
                    System.out.print("Enter Product Name: ");
                    productName = sc.nextLine();
                    System.out.print("Enter Cost: ");
                    productCost = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    productQuantity = sc.nextInt();
                    sc.nextLine();
                    ModifyProductData.addProduct(productName, productCost, productQuantity);
                    break;
                case 3:
                    System.out.println("Remove a Product from List");
                    System.out.print("Enter Product Name or ID: ");
                    productInfo = sc.nextLine();
                    if(isInteger(productInfo))
                        ModifyProductData.removeProduct(productInfo, 0);
                    else
                        ModifyProductData.removeProduct(productInfo, 1);
                    break;
                case 4:
                    System.out.println("Modify Existing Product");
                    modifyExistingProductMenu(sc);
                    break;
                case 5:
                    System.out.println("View Purchase History");
                    break;
                case 6:
                    System.out.println("View Customer Information");
                    break;
                case 0:
                    System.out.println("Exiting Seller Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void customerMenu(Scanner sc) {
        int choice;
        String customerId;
        String customerName;
        Double customerBalance;
        do {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. New Login");
            System.out.println("3. View Product List");
            System.out.println("4. View Purchase History");
            System.out.println("5. Buy Product");
            System.out.println("0. Previous Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("View Profile");
                    System.out.println("Enter Customer Name: ");
                    customerName = sc.nextLine();
                    customerId = RetriveCustomerData.getCustomerId(customerName);
                    ViewCustomerData.printCustomerProfile(customerId);
                    break;
                case 2:
                    System.out.println("New Customer Login");
                    System.out.println("Enter Customer Name: ");
                    customerName = sc.nextLine();
                    System.out.println("Enter Initial Balance: ");
                    customerBalance = sc.nextDouble();
                    sc.nextLine();
                    new Login(customerName, customerBalance);
                    break;
                case 3:
                    System.out.println("View Product List");
                    break;
                case 4:
                    System.out.println("View Purchase History");
                    break;
                case 5:
                    System.out.println("Buy Product");
                    buyProduct(sc);
                    break;
                case 0:
                    System.out.println("Exiting Customer Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void modifyExistingProductMenu(Scanner sc) {
        int choice;
        String productInfo;
        int quantity;
        Double cost;
        int productIdentifierIndex;

        do {
            System.out.println("\nProduct Menu:");
            System.out.println("1. Add Items");
            System.out.println("2. Remove Items");
            System.out.println("3. Change Quantity");
            System.out.println("4. Change Cost");
            System.out.println("0. Previoud Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product Name or ID: ");
                    productInfo = sc.nextLine();
                    System.out.print("Enter Number of Items to be added: ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    productIdentifierIndex = getProductIdentifierIndex(productInfo);
                    ModifyProductData.addItems(productInfo, productIdentifierIndex, quantity);                    
                    break;
                case 2:
                    System.out.print("Enter Product Name or ID: ");
                    productInfo = sc.nextLine();
                    System.out.print("Enter Number of Items to be removed: ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    productIdentifierIndex = getProductIdentifierIndex(productInfo);
                    ModifyProductData.removeItems(productInfo, productIdentifierIndex, quantity);                    
                    break;
                case 3:
                    System.out.print("Enter Product Name or ID: ");
                    productInfo = sc.nextLine();
                    System.out.print("Enter New Quantity: ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    productIdentifierIndex = getProductIdentifierIndex(productInfo);
                    ModifyProductData.modifyQuantity(productInfo, productIdentifierIndex, quantity);                   
                    break;
                case 4:
                    System.out.print("Enter Product Name or ID: ");
                    productInfo = sc.nextLine();
                    System.out.print("Enter New Cost: ");
                    cost = sc.nextDouble();
                    sc.nextLine();
                    productIdentifierIndex = getProductIdentifierIndex(productInfo);
                    ModifyProductData.modifyCost(productInfo, productIdentifierIndex, cost);            
                    break;
                case 0:
                    System.out.println("Exiting Product Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        } while (choice != 0);
    }

    private static void buyProduct(Scanner sc) {
        System.out.print("Enter Product Name or ID: ");
        String productInfo = sc.nextLine();
        int productIdentifierIndex = getProductIdentifierIndex(productInfo);

        int availableQuantity = RetriveData.getQuantity(productInfo, productIdentifierIndex);
        Double costPerItem = RetriveData.getCost(productInfo, productIdentifierIndex);    
        System.out.println("Available quantity: " + availableQuantity);
        System.out.println("Cost per item: " + costPerItem);

        System.out.print("Enter Required Quantity: ");
        int requiredQuantity = sc.nextInt();
        sc.nextLine();
        
        if (requiredQuantity > availableQuantity) {
            System.out.println("Not enough quantity left.");
            System.out.println("Required quantity: " + requiredQuantity);
            System.out.println("Available quantity: " + availableQuantity);

            System.out.println("\n1. Continue with the Purchase");
            System.out.println("2. Cancel");
            int choice1 = sc.nextInt();
            sc.nextLine();
            if(choice1 == 2) {
                System.out.println("Purchase canceled");
                return;
            }
            requiredQuantity = availableQuantity;
        }

        Double bill = requiredQuantity * costPerItem;
        System.out.println("Total Bill: " + bill);

        System.out.println("Enter Customer Name: ");
        String customerName = sc.nextLine();
        Double customerBalance = RetriveCustomerData.getBalance(customerName);

        if (customerBalance < bill) {
            System.out.println("Purchase Canceled. Balance not Sufficient.");
            return;
        }

        ModifyCustomerData.removeBalance(customerName, bill);
        System.out.println("Purchase Successful");
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getProductIdentifierIndex(String s) {
        if (isInteger(s)) {
            return 0;
        }
        return 1;
    }
}
