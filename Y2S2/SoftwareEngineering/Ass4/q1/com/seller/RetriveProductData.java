package com.seller;

import java.util.List;

public class RetriveProductData {
    public static int getNextProductId() {
        int nextProductId = 0;
        int currentProductId = 0;

        List<String> products = com.inventory.RetriveData.readProducts();
        for (String product : products) {
            String[] productDetails = product.split(", ");
            currentProductId = Integer.parseInt(productDetails[0]);
            if (currentProductId > nextProductId) {
                nextProductId = currentProductId;
            }
        }
        return nextProductId + 1;
    }
}
