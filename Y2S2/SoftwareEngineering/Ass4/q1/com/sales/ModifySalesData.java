package com.sales;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ModifySalesData {
    private static final String salesFile = "./database/purchase_history.csv";

    public static void writelines(List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(salesFile))) {
            for (String line : lines) {
                if (!line.isBlank())
                    pw.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
