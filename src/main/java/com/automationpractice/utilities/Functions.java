package com.automationpractice.utilities;

import java.util.Random;

public class Functions {
    public static String randomQuantityProduct(){
        int min = 2;
        int max = 5;
        int quantity = new Random().nextInt(max - min + 1) + min;
        return String.valueOf(quantity);
    }

    public static double refactorPrice(String price){
        String priceInText = price.replace("$", "");
        double priceInNumber = Double.parseDouble(priceInText);
        return priceInNumber;
    }
}
