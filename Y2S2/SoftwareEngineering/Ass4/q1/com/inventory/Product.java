package com.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Product {
    /*
     * for productId the identifierIndex = 0
     * for productname the identfierIndex = 1 
    */
    public static boolean isAvailable(String productDetail, int identifierIndex) {
        try(BufferedReader br = new BufferedReader(new FileReader("./database/inventory.csv"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productDetail.equals(products[identifierIndex])) {
                    return true;
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }

        return false;
    }

    public static double getCost(String productDetail, int identifierIndex) {
        try(BufferedReader br = new BufferedReader(new FileReader("./database/inventory.csv"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productDetail.equals(products[identifierIndex])) {
                    return Double.parseDouble(products[2]);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }

        return -1.0;
    }

    public static int availableQuantity(String productDetail, int identifierIndex) {
        try(BufferedReader br = new BufferedReader(new FileReader("./database/inventory.csv"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] products = line.split(", ");
                if(productDetail.equals(products[identifierIndex])) {
                    return Integer.parseInt(products[3]);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need inventory database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }

        return 0;
    }
}