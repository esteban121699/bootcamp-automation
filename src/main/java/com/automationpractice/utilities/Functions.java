package com.automationpractice.utilities;

import java.util.Random;

public class Functions {

    public static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String randomEmail(int n){
        String alphaNumeric = getAlphaNumericString(n);
        String email = alphaNumeric + System.nanoTime() + "@test.com";
        return email;
    }

    public static int randomNumber(int n){
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

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
