package com.dataprovider;

import org.testng.annotations.DataProvider;

public class ShoppingCartDataProvider {

    @DataProvider(name = "purchase process data")
    public Object[][] validAccountData() {
        return new Object[][] {
                {"2016@gmail.com", "123456", "this is a message about the order"}
        };
    }
}