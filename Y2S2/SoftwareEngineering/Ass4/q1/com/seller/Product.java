package com.seller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Product {
    private static final String filePath = "./database/inventory.csv";

    public static void addProduct(String productName, double cost, int quantity) {
        if(com.inventory.Product.isAvailable(productName, 1)) {
            System.out.println("Product already exists.");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lastline;
            int lastProductId = 1;
            br.readLine(); // ignore the header-line
            while((lastline = br.readLine()) != null) {
                if(!br.ready()) {
                    String[] product = lastline.split(", ");
                    lastProductId = Integer.parseInt(product[0]);
                }
            }

            String[] newProduct = {String.valueOf(lastProductId), productName, String.valueOf(cost), String.valueOf(quantity)};
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(String.join(", ", newProduct));
            pw.close();

            System.out.println("Cost has been mdified.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
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

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productIdentifier.equals(products[identifierIndex])) {
                    continue;
                }
                sb.append(String.join(", ", products)).append("\n");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(sb.toString());
            pw.close();

            System.out.println("Cost has been mdified.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void modifyCost(String productIdentifier, int identifierIndex, double newCost) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productIdentifier.equals(products[identifierIndex])) {
                    products[2] = String.valueOf(newCost);
                }
                sb.append(String.join(", ", products)).append("\n");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(sb.toString());
            pw.close();

            System.out.println("Cost has been mdified.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void addItems(String productIdentifier, int identifierIndex, double supply) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productIdentifier.equals(products[identifierIndex])) {
                    double prevVal = Double.parseDouble(products[3]);
                    prevVal += supply;
                    products[3] = String.valueOf(prevVal);
                }
                sb.append(String.join(", ", products)).append("\n");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(sb.toString());
            pw.close();

            System.out.println("Items has been added.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void removeItems(String productIdentifier, int identifierIndex, double demand) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productIdentifier.equals(products[identifierIndex])) {
                    double prevVal = Double.parseDouble(products[3]);
                    if(prevVal < demand) {
                        System.out.println("Not enough items.\n Request Canceled.");
                        return;
                    }
                    prevVal -= demand;
                    products[3] = String.valueOf(prevVal);
                }
                sb.append(String.join(", ", products)).append("\n");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(sb.toString());
            pw.close();

            System.out.println("Supply has been added.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void modifyQuantity(String productIdentifier, int identifierIndex, double newQuantity) {
        if(!com.inventory.Product.isAvailable(productIdentifier, identifierIndex)) {
            System.out.println("Product does not exist.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productIdentifier.equals(products[identifierIndex])) {
                    products[3] = String.valueOf(newQuantity);
                }
                sb.append(String.join(", ", products)).append("\n");
            }

            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.print(sb.toString());
            pw.close();

            System.out.println("Supply has been added.");
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
