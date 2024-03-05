package com.customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetriveCustomerData {
    private static final String customerFile = "./database/customers.csv";

    public static List<String> readCustomerInfo() {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            br.readLine(); // skip the header-line
            String line;
            while((line = br.readLine()) != null) {
                if (!line.isBlank())
                    lines.add(line);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need customer database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
        return lines;
    }

    public static boolean isAvailableId(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAvailableName(String customerName) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerName.equals(customer[1])) {
                return true;
            }
        }
        return false;
    }

    public static String getCustomerId(String customerName) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerName.equals(customer[1])) {
                return customer[0];
            }
        }
        System.out.println("Customer does not exist.");
        return null;
    }

    public static String getName(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return customer[1];
            }
        }
        System.out.println("Customer does not exist.");
        return null;
    }

    public static double getBalance(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return Double.parseDouble(customer[2]);
            }
        }
        System.out.println("Customer does not exist.");
        return 0;
    }

    public static int getSuccessfulPurchases(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return Integer.parseInt(customer[3]);
            }
        }
        System.out.println("Customer does not exist.");
        return 0;
    }

    public static int getFailedPurchases(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return Integer.parseInt(customer[4]);
            }
        }
        System.out.println("Customer does not exist.");
        return 0;
    }

    public static double getTotalSpendings(String customerId) {
        List<String> lines = readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                return Double.parseDouble(customer[5]);
            }
        }
        System.out.println("Customer does not exist.");
        return 0;
    }

    public static String getNextCustomerId() {
        int nextCustomerId = 0;
        int currentCustomerId = 0;

        List<String> customers = readCustomerInfo();
        for (String customer : customers) {
            String[] customerDetails = customer.split(", ");
            currentCustomerId = Integer.parseInt(customerDetails[0]);
            if (currentCustomerId > nextCustomerId) {
                nextCustomerId = currentCustomerId;
            }
        }
        return String.valueOf(nextCustomerId + 1);
    }
}
