package com.seller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.inventory.*;

public class UploadProductData {
    private static final String filePath = "./database/inventory.csv";

    public static void addProduct(String productName, double cost, int quantity) {
        String productId = RetriveData.getProductId(productName);
        if(productId == null && com.inventory.RetriveData.isAvailable(productId)) {
            System.out.println("Product already exists. Choose a different name");
            return;
        }

        if(isInteger(productName)) {
            System.out.println("Product name can not be an integer");
            return;
        }

        if(cost <= 0.0) {
            System.out.println("Cost has to be positive value");
            return;
        }

        if(quantity < 0) {
            System.out.println("Quantity can not be negative");
            return;
        }

        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));) {
            int nextProductId = RetriveProductData.getNextProductId();
            String[] newProduct = {String.valueOf(nextProductId), productName, String.valueOf(cost), String.valueOf(quantity)};
            
            pw.print("\n" + String.join(", ", newProduct));

            System.out.println("New product added.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
