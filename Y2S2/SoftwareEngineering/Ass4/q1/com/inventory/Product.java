package com.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private static final String filePath = "./database/inventory.csv";

    public static List<String> readProducts() {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip the header-line
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
        return lines;
    }

    /*
     * for productId the identifierIndex = 0
     * for productname the identfierIndex = 1 
    */
    public static boolean isAvailable(String productIdentifier, int identifierIndex) {
        List<String> lines = readProducts();
        for (String product : lines) {
            String[] products = product.split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                return true;
            }
        }
        return false;
    }

    public static double getCost(String productIdentifier, int identifierIndex) {
        List<String> lines = readProducts();
        for (String product : lines) {
            String[] products = product.split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                return Double.parseDouble(products[2]);
            }
        }
        System.out.println("Product doesn't exist");
        return -1.0;
    }

    public static int getQuantity(String productIdentifier, int identifierIndex) {
        List<String> lines = readProducts();
        for (String product : lines) {
            String[] products = product.split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                return Integer.parseInt(products[3]);
            }
        }
        System.out.println("Product doesn't exist");
        return -1;
    }
}