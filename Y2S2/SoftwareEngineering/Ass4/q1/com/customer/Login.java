package com.customer;

public class Login {
    private String customerID;
    private String customerName;
    private Double balance; 
    private int number_of_successful_purchases;
    private int number_of_failed_purchases;
    private double total_spending;

    public Login(String customerId, String customerName, Double balance) {
        if(CustomerData.isAvailable(customerId)) {
            System.out.println("Customer already exists.");
            this.customerID = customerId;
            this.customerName = 
                    CustomerData.getName(customerId);
            this.balance = 
                    CustomerData.getBalance(customerId);   
            this.number_of_successful_purchases = 
                    CustomerData.getSuccessfulPurchases(customerId);
            this.number_of_failed_purchases = 
                    CustomerData.getFailedPurchases(customerId);
            this.total_spending = 
                    CustomerData.getTotalSpendings(customerId);
        }
        else {
            this.customerID = customerId;
            this.customerName = customerName;
            CustomerCSV.addCustomer(this);
        }
    }

    public String getCustomerID() {
        return customerID;
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
}
