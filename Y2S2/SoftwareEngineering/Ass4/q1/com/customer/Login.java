package com.customer;

public class Login {
    private String customerID;
    private int number_of_successful_purchases;
    private int number_of_failed_purchases;
    private double total_spending;

    public Login(String customerId) {
        if(CustomerData.isAvailable(customerId)) {
            System.out.println("Customer already exists.");
            this.customerID = customerId;
            this.number_of_successful_purchases = 
                    CustomerData.getSuccessfulPurchases(customerId);
            this.number_of_failed_purchases = 
                    CustomerData.getFailedPurchases(customerId);
            this.total_spending = 
                    CustomerData.getTotalSpendings(customerId);
        }
        else {
            this.customerID = customerId;
            CustomerCSV.addCustomer(this);
        }
    }

    public String getCustomerID() {
        return customerID;
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
