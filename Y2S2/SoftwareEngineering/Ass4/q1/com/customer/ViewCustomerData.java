package com.customer;

import java.util.List;

public class ViewCustomerData {
    public static void printAllCustomerInfo() {
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            System.out.println(customer[0] + "\t" + customer[1] + "\t" + 
                               customer[2] + "\t" + customer[3] + "\t" +
                               customer[4] + "\t" + customer[5]);
        }
    }

    public static void printCustomerProfile(String customerId) {
        List<String> lines = RetriveCustomerData.readCustomerInfo();
        for (String line : lines) {
            String[] customer = line.split(", ");
            if(customerId.equals(customer[0])) {
                System.out.println(customer[0] + "\t" + customer[1] + "\t" + 
                                   customer[2] + "\t" + customer[3] + "\t" +
                                   customer[4] + "\t" + customer[5]);
            }
        }
    }
}
