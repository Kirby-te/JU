package com.customer;

public class ViewCustomerData {
    public static void printCustomerProfile(String customerId) {
        System.out.println("  CustomerId " + " Name " + " Balance " + " Successful Purchases " + " Failed Purchases " + " Total Spendings ");
        System.out.println("\t" + customerId +
                           "\t" + RetriveCustomerData.getName(customerId) +
                           "\t" + RetriveCustomerData.getBalance(customerId) +
                           "\t" + RetriveCustomerData.getSuccessfulPurchases(customerId) + 
                           "\t" + RetriveCustomerData.getFailedPurchases(customerId) + 
                           "\t" + RetriveCustomerData.getTotalSpendings(customerId));
    }
}
