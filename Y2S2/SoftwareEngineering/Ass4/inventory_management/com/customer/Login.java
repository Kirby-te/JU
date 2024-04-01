package com.customer;

public class Login {
    private String customerId;
    private String customerName;
    private Double balance; 
    private int number_of_successful_purchases;
    private int number_of_failed_purchases;
    private double total_spending;

    public Login(String customerName, Double balance) {
        if(isInteger(customerName)) {
            System.out.println("Name can not be a number");
            System.out.println("Login Failed");
            return;
        }
        if(balance < 0) {
            System.out.println("Balance can not be negative");
            System.out.println("Login Failed");
            return;
        }
        if(RetriveCustomerData.isAvailableName(customerName)) {
            System.out.println("Customer already exists.");
            this.customerId =
                    RetriveCustomerData.getCustomerId(customerName);
            this.customerName = customerName;
            this.balance = 
                    RetriveCustomerData.getBalance(this.customerId);   
            this.number_of_successful_purchases = 
                    RetriveCustomerData.getSuccessfulPurchases(this.customerId);
            this.number_of_failed_purchases = 
                    RetriveCustomerData.getFailedPurchases(this.customerId);
            this.total_spending = 
                    RetriveCustomerData.getTotalSpendings(this.customerId);
        }
        else {
            this.customerId = RetriveCustomerData.getNextCustomerId();
            this.customerName = customerName;
            this.balance = balance;
            UploadCustomerData.addCustomer(this);
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public int getNumber_of_successful_purchases() {
        return number_of_successful_purchases;
    }

    public int getNumber_of_failed_purchases() {
        return number_of_failed_purchases;
    }

    public double getTotal_spending() {
        return total_spending;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
