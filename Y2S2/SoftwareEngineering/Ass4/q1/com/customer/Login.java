package com.customer;

public class Login {
    private String customerId;
    private String customerName;
    private Double balance; 
    private int number_of_successful_purchases;
    private int number_of_failed_purchases;
    private double total_spending;

    public Login(String customerName, Double balance) {
        if(RetriveData.isAvailable(customerName)) {
            System.out.println("Customer already exists.");
            this.customerId =
                    RetriveData.getCustomerId(customerName);
            this.customerName = customerName;
            this.balance = 
                    RetriveData.getBalance(this.customerId);   
            this.number_of_successful_purchases = 
                    RetriveData.getSuccessfulPurchases(this.customerId);
            this.number_of_failed_purchases = 
                    RetriveData.getFailedPurchases(this.customerId);
            this.total_spending = 
                    RetriveData.getTotalSpendings(this.customerId);
        }
        else {
            this.customerId = RetriveData.getNextCustomerId();
            this.customerName = customerName;
            this.balance = balance;
            UploadData.addCustomer(this);
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
}
