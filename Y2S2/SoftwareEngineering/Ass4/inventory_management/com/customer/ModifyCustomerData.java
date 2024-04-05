package com.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ModifyCustomerData {
    private static final String customerFile = "./database/customers.csv";

    public static void writeCustomers(List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(customerFile))) {
            for (String line : lines) {
                if (!line.isBlank())
                    pw.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addBalance(String customerId, Double credit) {
        if(!RetriveCustomerData.isAvailableId(customerId)) {
            System.out.println("Customer does not exist.");
            return;
        }
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        // ignore header
        for (int i=1; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                Double currentBalance = Double.parseDouble(customer[2]);
                customer[2] = String.valueOf(currentBalance + credit);
                System.out.println(customer[2]);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
    }

    public static void removeBalance(String customerId, Double debit) {
        if(!RetriveCustomerData.isAvailableId(customerId)) {
            System.out.println("Customer does not exist.");
            return;
        }
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        // ignore header
        for (int i=1; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                Double currentBalance = Double.parseDouble(customer[2]);
                if(currentBalance < debit) {
                    System.out.println("Not Enough Balance.");
                    return;
                }
                customer[2] = String.valueOf(currentBalance - debit);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
    }

    public static void incrementSuccessfulPurchases(String customerId) {
        if(!RetriveCustomerData.isAvailableId(customerId)) {
            System.out.println("Customer does not exist.");
            return;
        }
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        // ignore header
        for (int i=1; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                int sucPur = Integer.parseInt(customer[3]);
                customer[3] = String.valueOf(sucPur+1);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
    }

    public static void incrementFailedPurchases(String customerId) {
        if (!RetriveCustomerData.isAvailableId(customerId)) {
            System.out.println("Customer does not exist");
            return;
        }
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        // ignore header
        for (int i=1; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                int failPur = Integer.parseInt(customer[4]);
                customer[4] = String.valueOf(failPur+1);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
    }

    public static void increaseSpendings(String customerId, Double cost) {
        if (!RetriveCustomerData.isAvailableId(customerId)) {
            System.out.println("Customer does not exist");
            return;
        }
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        // ignore header
        for (int i=1; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                Double currentSpending = Double.parseDouble(customer[5]);
                customer[5] = String.valueOf(currentSpending + cost);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
    }
}
