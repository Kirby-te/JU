package com.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetriveSalesData {
    private static final String salesFile = "./database/purchase_history.csv";

    public static List<String> readSalesInfo() {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while((line = br.readLine()) != null) {
                if (!line.isBlank())
                    lines.add(line);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Need sales database.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
        return lines;
    }
}
