package com.customer;

public class ViewData {
    public static void printCustomerProfile(String customerId) {
        System.out.println("\tCustomerId" + "\tSuccessful Purchases" + "\tFailed Puchases" + "\tTotal Spendings");
        System.out.println("\t" + customerId +
                           "\t" + RetriveData.getName(customerId) +
                           "\t" + RetriveData.getBalance(customerId) +
                           "\t" + RetriveData.getSuccessfulPurchases(customerId) + 
                           "\t" + RetriveData.getFailedPurchases(customerId) + 
                           "\t" + RetriveData.getTotalSpendings(customerId));
    }
}
