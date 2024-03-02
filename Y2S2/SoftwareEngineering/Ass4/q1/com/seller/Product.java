package com.seller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private static final String filePath = "./database/inventory.csv";

    private static List<String> readProducts() {
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

    public static int getNextProductId() {
        int nextProductId = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lastline;
            br.readLine(); // skip the header-line
            while((lastline = br.readLine()) != null) {
                String[] product = lastline.split(", ");
                int val = Integer.parseInt(product[0]);
                if(val > nextProductId) {
                    nextProductId = val;
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
        return nextProductId + 1;
    }

    public static void addProduct(String productName, double cost, int quantity) {
        if(com.inventory.Product.isAvailable(productName, 1)) {
            System.out.println("Product already exists.");
            return;
        }

        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath));) {
            int nextProductId = getNextProductId();
            String[] newProduct = {String.valueOf(nextProductId), productName, String.valueOf(cost), String.valueOf(quantity)};
            
            pw.print(String.join(", ", newProduct));
            pw.close();

            System.out.println("New product added.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void removeProduct(String productIdentifier, int identifierIndex) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = readProducts();
        lines.removeIf(line -> (line.split(", ")[identifierIndex].equals(productIdentifier)));
        writeProducts(lines);
        System.out.println("Product removed.");
    }

    public static void modifyCost(String productIdentifier, int identifierIndex, double newCost) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = readProducts();
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

    public static void modifyQuantity(String productIdentifier, int identifierIndex, double newQuantity) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = readProducts();
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

    public static void addItems(String productIdentifier, int identifierIndex, double supply) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        List<String> lines = readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                double prevVal = Double.parseDouble(products[3]);
                prevVal += supply;
                products[3] = String.valueOf(prevVal);
                lines.set(i, String.join(", ", products));
                break;
            }
        }
        writeProducts(lines);
        System.out.println("Items added.");
    }

    public static void removeItems(String productIdentifier, int identifierIndex, double demand) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }
        
        List<String> lines = readProducts();
        for(int i = 0; i < lines.size(); i++) {
            String[] products = lines.get(i).split(", ");
            if(productIdentifier.equals(products[identifierIndex])) {
                double prevVal = Double.parseDouble(products[3]);
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
