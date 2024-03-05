package com.seller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadProductData {
    private static final String filePath = "./database/inventory.csv";

    public static void addProduct(String productName, double cost, int quantity) {
        if(com.inventory.RetriveData.isAvailable(productName, 1)) {
            System.out.println("Product already exists.");
            return;
        }

        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));) {
            int nextProductId = RetriveProductData.getNextProductId();
            String[] newProduct = {String.valueOf(nextProductId), productName, String.valueOf(cost), String.valueOf(quantity)};
            
            pw.print("\n" + String.join(", ", newProduct));
            pw.close();

            System.out.println("New product added.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
