package com.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ModifyData {
    private static final String customerFile = "./database/customers.csv";

    public static void writeCustomers(List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(customerFile, true))) {
            for (String line : lines) {
                if (line.isBlank())
                    pw.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addBalance(String customerId, Double credit) {
        List<String> lines = RetriveData.readCustomerInfo();
        for (int i=0; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                Double currentBalance = Double.parseDouble(customer[2]);
                customer[2] = String.valueOf(currentBalance + credit);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
        System.out.println("Customer does not exist.");
    }

    public static void removeBalance(String customerId, Double debit) {
        List<String> lines = RetriveData.readCustomerInfo();
        for (int i=0; i<lines.size(); i++) {
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
        System.out.println("Customer does not exist.");
    }

    public static void incrementSuccessfulPurchases(String customerId) {
        List<String> lines = RetriveData.readCustomerInfo();
        for (int i=0; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                int sucPur = Integer.parseInt(customer[3]);
                customer[3] = String.valueOf(sucPur++);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
        System.out.println("Customer does not exist.");
    }

    public static void incrementFailedPurchases(String customerId) {
        List<String> lines = RetriveData.readCustomerInfo();
        for (int i=0; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                int failPur = Integer.parseInt(customer[4]);
                customer[4] = String.valueOf(failPur++);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
        System.out.println("Customer does not exist.");
    }

    public static void modifySpendings(String customerId, Double cost) {
        List<String> lines = RetriveData.readCustomerInfo();
        for (int i=0; i<lines.size(); i++) {
            String[] customer = lines.get(i).split(", ");
            if(customerId.equals(customer[0])) {
                Double currentSpending = Double.parseDouble(customer[5]);
                customer[5] = String.valueOf(currentSpending + cost);
                lines.set(i, String.join(", ", customer));
                break;
            }
        }
        writeCustomers(lines);
        System.out.println("Customer does not exist.");
    }
}
