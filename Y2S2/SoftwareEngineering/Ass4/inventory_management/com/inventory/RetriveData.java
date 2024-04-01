package com.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetriveData {
    private static final String filePath = "./database/inventory.csv";

    public static List<String> readProducts() {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                if (!line.isBlank())
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
     * for productId the 0 = 0
     * for productname the identfierIndex = 1 
    */
    public static boolean isAvailable(String productID) {
        if (productID == null) {
            return false;
        }

        List<String> lines = readProducts();
        for (String product : lines) {
            if (product.isBlank())
                continue;
            String[] products = product.split(", ");
            if(productID.equals(products[0])) {
                return true;
            }
        }
        return false;
    }

    public static String getProductId(String productName) {
         if (productName == null) {
            return null;
        }

        List<String> lines = readProducts();
        for (String product : lines) {
            if (product.isBlank())
                continue;
            String[] products = product.split(", ");
            if(productName.equals(products[1])) {
                return products[0];
            }
        }
        return null;
    }

    public static String getProductName(String productId) {
        if (productId == null) {
            return null;
        }

        List<String> lines = readProducts();
        for (String product : lines) {
            if (product.isBlank())
                continue;
            String[] products = product.split(", ");
            if(productId.equals(products[0])) {
                return products[1];
            }
        }
        return null;
    }

    public static double getCost(String productID) {
        if (productID == null) {
            return 0;
        }

        List<String> lines = readProducts();
        for (String product : lines) {
            if (product.isBlank())
                continue;
            String[] products = product.split(", ");
            if(productID.equals(products[0])) {
                return Double.parseDouble(products[2]);
            }
        }
        return -1.0;
    }

    public static int getQuantity(String productID) {
        if (productID == null) {
            return 0;
        }

        List<String> lines = readProducts();
        for (String product : lines) {
            if (product.isBlank())
                continue;
            String[] products = product.split(", ");
            if(productID.equals(products[0])) {
                return Integer.parseInt(products[3]);
            }
        }
        return -1;
    }
}