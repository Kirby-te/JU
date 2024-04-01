package com.sales;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class UploadSalesData {
    private static final String salesFile = "./database/purchase_history.csv";

    public static void addPurchase(String customerId, String productId, 
            String purchaseStatus, int quantity, Double cost) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(salesFile, true));) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            
            String[] newPurchase = {
                customerId,
                productId,
                purchaseStatus,
                String.valueOf(quantity),
                String.valueOf(cost),
                ts.toString()
            };
            
            pw.print("\n" + String.join(", ", newPurchase));
            pw.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    } 
}
