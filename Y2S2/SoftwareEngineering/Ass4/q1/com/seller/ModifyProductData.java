package com.seller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ModifyProductData {
    private static final String filePath = "./database/inventory.csv";

    /*
     * creating new file every-time with neccesry modifications
     * using this to change certain produxt value
    */
    private static void writeProducts(List<String> lines) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("product_id, product_name, cost_per_item, quantity");// header
            for (String line : lines) {
                pw.println(line);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void removeProduct(String productIdentifier, int identifierIndex) {
        if(!com.inventory.RetriveData.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }
        
        List<String> lines = com.inventory.RetriveData.readProducts();
        lines.removeIf(line -> (line.split(", ")[identifierIndex].equals(productIdentifier)));
        writeProducts(lines);
        System.out.println("Product removed.");
    }

    public static void modifyCost(String productIdentifier, int identifierIndex, double newCost) {
        if(!com.inventory.RetriveData.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = com.inventory.RetriveData.readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                products[2] = String.valueOf(newCost);
                lines.set(i, String.join(", ", products));
                break;
            }
        }
        writeProducts(lines);
        System.out.println("Cost modified.");
    }

    public static void modifyQuantity(String productIdentifier, int identifierIndex, int newQuantity) {
        if(!com.inventory.RetriveData.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = com.inventory.RetriveData.readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                products[3] = String.valueOf(newQuantity);
                lines.set(i, String.join(", ", products));
                break;
            }
        }
        writeProducts(lines);
        System.out.println("Quantity modified.");
    }

    public static void addItems(String productIdentifier, int identifierIndex, int supply) {
        if(!com.inventory.RetriveData.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = com.inventory.RetriveData.readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                int prevVal = Integer.parseInt(products[3]);
                prevVal += supply;
                products[3] = String.valueOf(prevVal);
                lines.set(i, String.join(", ", products));
                break;
            }
        }
        writeProducts(lines);
        System.out.println("Items added.");
    }

    public static void removeItems(String productIdentifier, int identifierIndex, int demand) {
        if(!com.inventory.RetriveData.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }
        
        List<String> lines = com.inventory.RetriveData.readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                int prevVal = Integer.parseInt(products[3]);
                if(prevVal < demand) {
                    System.out.println("Not enough items.\n Request Canceled.");
                    return;
                }
                prevVal -= demand;
                products[3] = String.valueOf(prevVal);
                lines.set(i, String.join(", ", products));
                break;
            }
        }
        writeProducts(lines);
        System.out.println("Removed required items.");
    }
}
