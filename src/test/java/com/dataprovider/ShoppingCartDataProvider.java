package com.dataprovider;

import com.automationpractice.utilities.Functions;
import org.testng.annotations.DataProvider;

public class ShoppingCartDataProvider {

    int mailLength = 3;
    int passwordLength = 8;
    int zipLength = 5;
    int phoneLength = 9;

    @DataProvider(name = "register account data")
    public Object[][] registerAccountData() {
        return new Object[][] {
                {"Esteban Fernando", "Castillo Camizán", Functions.randomEmail(mailLength), Functions.getAlphaNumericString(passwordLength), "Mz B2 Lt9 San Camilo, José Gálvez", "Lima", "Alaska", Functions.randomNumber(zipLength), Functions.randomNumber(phoneLength)}
        };
    }

    @DataProvider(name = "purchase process data")
    public Object[][] purchaseProcessData() {
        return new Object[][] {
                {"this is a message about the order"}
        };
    }
}