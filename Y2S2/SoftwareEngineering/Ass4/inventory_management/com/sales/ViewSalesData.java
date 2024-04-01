package com.sales;

import java.util.List;

public class ViewSalesData {
    public static void printPurchase() {
        List<String> lines = RetriveSalesData.readSalesInfo();
        int count = 0;
        for (String line : lines) {
            count++;
            String[] purchase = line.split(", ");
            System.out.println(purchase[0] + "\t" + purchase[1] + "\t" + 
                               purchase[2] + "\t" + purchase[3] + "\t" +
                               purchase[4] + "\t" + purchase[5]);
        }
        System.out.println("Total Purchases: " + count);
    }

    public static void printCustomerPurchase(String customerId) {
        List<String> lines = RetriveSalesData.readSalesInfo();
        int count = 0;
        for (String line : lines) {
            String[] purchase = line.split(", ");
            if(customerId.equals(purchase[0])) {
                count++;
                System.out.println(purchase[0] + "\t" + purchase[1] + "\t" + 
                                   purchase[2] + "\t" + purchase[3] + "\t" +
                                   purchase[4] + "\t" + purchase[5]);
            }
        }
        System.out.println("Total Purchases: " + count);
    }

    public static void printProductPurchase(String productId) {
        List<String> lines = RetriveSalesData.readSalesInfo();
        int count = 0;
        for (String line : lines) {
            String[] purchase = line.split(", ");
            if(productId.equals(purchase[1])) {
                count++;
                System.out.println(purchase[0] + "\t" + purchase[1] + "\t" + 
                                   purchase[2] + "\t" + purchase[3] + "\t" +
                                   purchase[4] + "\t" + purchase[5]);
            }
        }
        System.out.println("Total Purchases: " + count);
    }
}
