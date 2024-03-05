package com.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadCustomerData {
    private static final String customerFile = "./database/customers.csv";

    public static void addCustomer(Login details) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(customerFile, true));) {
            String[] newCustomer = {
                details.getCustomerId(),
                details.getCustomerName(),
                String.valueOf(details.getBalance()),
                String.valueOf(details.getNumber_of_successful_purchases()), 
                String.valueOf(details.getNumber_of_failed_purchases()), 
                String.valueOf(details.getTotal_spending())
            };
            
            pw.print("\n" + String.join(", ", newCustomer));
            pw.close();

            System.out.println("New Customer added.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    } 
}
