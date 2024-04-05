package com.seller;

import java.util.List;

public class RetriveProductData {
    public static int getNextProductId() {
        int nextProductId = 0;
        int currentProductId = 0;

        List<String> products = com.inventory.RetriveData.readProducts();
        // ignore the header
        for (int i=1; i<products.size(); i++) {
            String[] productDetails = products.get(i).split(", ");
            currentProductId = Integer.parseInt(productDetails[0]);
            if (currentProductId > nextProductId) {
                nextProductId = currentProductId;
            }
        }
        return nextProductId + 1;
    }
}
