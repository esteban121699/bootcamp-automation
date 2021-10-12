package com.automationpractice.shoppingtest;

import com.automationpractice.Base;
import com.dataprovider.ShoppingCartDataProvider;
import com.pageobjects.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCart extends Base{
    private MainPage mainPage;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private int maxProductsCart = 3;
    private int expectedSizeThumbImages = 4;

    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        mainPage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(mainPage.getUrl());
        mainPage.clickOnSignIn();
    }

    @Test(dataProvider = "purchase process data", dataProviderClass = ShoppingCartDataProvider.class)
    public void testPurchasingProcess(String email, String password) throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        authenticationPage.fillLoginForm(email, password);
        myAccountPage = new MyAccountPage(driver);
        softAssert.assertTrue(myAccountPage.lblMyAccountIsDisplayed());
        myAccountPage.clickOnWomenTab();
        categoryPage = new CategoryPage(driver);
        categoryPage.scrollToCategoryName();

        for (int index = 0; index < 3; index++) {
            categoryPage.mouseOverToProductItem(index);
            productPage = new ProductPage(driver);
            productPage.showProductInformation();
            productPage.changeProductQuantity();
            int sizeThumbImages = productPage.sizeThumbProductImages();
            softAssert.assertEquals(sizeThumbImages, expectedSizeThumbImages);
            productPage.changeProductSize();
            productPage.addProductToCart(index, maxProductsCart);
        }

        softAssert.assertAll();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
