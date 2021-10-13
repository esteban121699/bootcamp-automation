package com.automationpractice.shoppingtest;

import com.automationpractice.Base;
import com.dataprovider.ShoppingCartDataProvider;
import com.pageobjects.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.automationpractice.utilities.Functions.refactorPrice;
import static com.dataprovider.GlobalDataProvider.*;

public class ShoppingCart extends Base{
    private MainPage mainPage;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private OrderPage orderPage;
    private int maxProductsCart;
    private int expectedSizeThumbProductImages;
    private String expectedTextOrderConfirmation;
    private String expectedTextProductCounter;

    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        mainPage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        maxProductsCart = MAX_PRODUCTS_CART;
        expectedSizeThumbProductImages = EXPECTED_SIZE_THUMB_PRODUCT_IMAGES;
        expectedTextOrderConfirmation = EXPECTED_TEXT_ORDER_CONFIRMATION;
        expectedTextProductCounter = EXPECTED_TEXT_PRODUCT_COUNTER;
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(mainPage.getUrl());
        mainPage.clickOnSignIn();
    }

    @Test(dataProvider = "purchase process data", dataProviderClass = ShoppingCartDataProvider.class)
    public void testPurchasingProcess(String email, String password, String message) {

        SoftAssert softAssert = new SoftAssert();

        // 2. Sign in with an existing account
        authenticationPage.fillLoginForm(email, password);
        myAccountPage = new MyAccountPage(driver);
        softAssert.assertTrue(myAccountPage.lblMyAccountIsDisplayed());
        // 3. Enter the women tab
        myAccountPage.clickOnWomenTab();

        Object [][] shoopingCartProducts = new Object[maxProductsCart][3];

        for (int i = 0; i < maxProductsCart; i++) {
            categoryPage = new CategoryPage(driver);
            // 4. Enter the product detail
            categoryPage.mouseOverToProductItem(i);
            productPage = new ProductPage(driver);
            // 5. Print in console product description and price, change quantity product
            productPage.showProductInformation();
            productPage.changeProductQuantity();
            // 6. Check 4 images and change product size
            int sizeThumbProductImages = productPage.getSizeThumbProductImages();
            softAssert.assertEquals(sizeThumbProductImages, expectedSizeThumbProductImages);
            productPage.changeProductSize();
            // 7. Add product to shopping cart
            Object[] product = productPage.getDataProduct();
            for (int j = 0; j < product.length; j++) {
                shoopingCartProducts[i][j] = product[j];
            }

            productPage.addProductToCart(i, maxProductsCart);
        }
        // 8. Repeat the process 2 times according to the loop

        // 9. Do click on check out
        productPage.goToCheckout();

        // 10. Check shopping cart details
        System.out.println("SHOOPING CART-------------------------------------------");
        System.out.println("SKU 1 --> " + shoopingCartProducts[0][0]);
        System.out.println("PRICE 1 -->  " + shoopingCartProducts[0][1]);
        System.out.println("QUANTITY 1 -->  " + shoopingCartProducts[0][2]);

        System.out.println("SKU 2 --> " + shoopingCartProducts[1][0]);
        System.out.println("PRICE 2 -->  " + shoopingCartProducts[1][1]);
        System.out.println("QUANTITY 2 -->  " + shoopingCartProducts[1][2]);

        System.out.println("SKU 3 --> " + shoopingCartProducts[2][0]);
        System.out.println("PRICE 3 -->  " + shoopingCartProducts[2][1]);
        System.out.println("QUANTITY 3 -->  " + shoopingCartProducts[2][2]);

        orderPage = new OrderPage(driver);

        Object [][] orderItems = orderPage.getDataProducts(maxProductsCart);

        System.out.println("ORDER ITEMS-------------------------------------------");

        System.out.println("SKU 1 --> " + orderItems[0][0]);
        System.out.println("PRICE 1 -->  " + orderItems[0][1]);
        System.out.println("QUANTITY 1 -->  " + orderItems[0][2]);
        System.out.println("TOTAL 1 -->  " + orderItems[0][3]);

        System.out.println("SKU 2 --> " + orderItems[1][0]);
        System.out.println("PRICE 2 -->  " + orderItems[1][1]);
        System.out.println("QUANTITY 2 -->  " + orderItems[1][2]);
        System.out.println("TOTAL 1 -->  " + orderItems[1][3]);

        System.out.println("SKU 3 --> " + orderItems[2][0]);
        System.out.println("PRICE 3 -->  " + orderItems[2][1]);
        System.out.println("QUANTITY 3 -->  " + orderItems[2][2]);
        System.out.println("TOTAL 1 -->  " + orderItems[2][3]);

        System.out.println("VALIDATIONS-------------------------------------------");

        double expectedTotalProduct = refactorPrice(orderPage.getTextTotalProduct());
        double currentTotalProduct = 0;
        for (int i = 0; i < maxProductsCart; i++) {
            double totalItem = refactorPrice(orderItems[i][1].toString()) * Integer.parseInt(orderItems[i][2].toString());
            currentTotalProduct = currentTotalProduct + totalItem;

            String expectedUnitPriceProduct = shoopingCartProducts[i][1].toString();
            String currentUnitPriceProduct = orderItems[i][1].toString();

            // 10.2 Verify that the unit price for each product is correct
            softAssert.assertEquals(currentUnitPriceProduct, expectedUnitPriceProduct);
            System.out.println("EXPECTED UNIT PRICE " + (i + 1) + "-->  " + expectedUnitPriceProduct);
            System.out.println("CURRENT UNIT PRICE " + (i + 1) + "-->  " + currentUnitPriceProduct);

            String expectedQuantityProduct = shoopingCartProducts[i][2].toString();
            String currentQuantityProduct = orderItems[i][2].toString();

            // 10.3 Verify that the quantity for each product is correct
            softAssert.assertEquals(currentQuantityProduct, expectedQuantityProduct);
            System.out.println("EXPECTED QUANTITY " + (i + 1) + "-->  " + expectedQuantityProduct);
            System.out.println("CURRENT QUANTITY " + (i + 1) + "-->  " + currentQuantityProduct);
        }
        // 10.1 Check if the page is correctly adding the prices of each product with the total products price
        softAssert.assertEquals(currentTotalProduct, expectedTotalProduct);

        System.out.println("EXPECTED TOTAL -->  " + expectedTotalProduct);
        System.out.println("CURRENT TOTAL -->  " + currentTotalProduct);

        double expectedTotalPrice = refactorPrice(orderPage.getTextTotalPrice());
        double currentTotalPrice = orderPage.getPriceSum();

        // 10.4 Check that the total price is correct
        softAssert.assertEquals(currentTotalPrice, expectedTotalPrice);

        System.out.println("EXPECTED TOTAL PRICE -->  " + expectedTotalPrice);
        System.out.println("CURRENT TOTAL PRICE -->  " + currentTotalPrice);

        String currentTextProductCounter = orderPage.getTextProductCounter();

        // 10.5 Check product counter text in the upper right
        softAssert.assertEquals(currentTextProductCounter, expectedTextProductCounter);

        System.out.println("EXPECTED COUNTER TEXT -->  " + expectedTextProductCounter);
        System.out.println("CURRENT COUNTER TEXT -->  " + currentTextProductCounter);

        // 11. Do click on proceed to check out
        orderPage.goToProceedToCheckout();

        // 12. Add a message and do click on proceed to check out
        orderPage.fillForm(message);

        // 13. Add terms and conditions and do click on proceed to check out
        orderPage.acceptTermsAndConditions();

        // 14. Do click on pay by bank wire and do click on confirm order
        orderPage.chooseMethodPayment();

        // 15. Verify the successful transaction message
        String titleOrderConfirmation = orderPage.getTextOrderConfirmation();
        softAssert.assertEquals(titleOrderConfirmation, expectedTextOrderConfirmation);

        softAssert.assertAll();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
