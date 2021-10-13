package com.automationpractice.shoppingtest;

import com.automationpractice.Base;
import com.automationpractice.utilities.Functions;
import com.dataprovider.ShoppingCartDataProvider;
import com.pageobjects.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.dataprovider.GlobalDataProvider.*;

public class ShoppingCart extends Base{
    private MainPage mainPage;
    private SignUpPage signUpPage;
    private SignInPage signInPage;
    private MyAccountPage myAccountPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private OrderPage orderPage;
    private String formEmail;
    private String formPassword;

    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        // 1. Go to automationpractice.com
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(mainPage.getUrl());
        mainPage.clickOnSignIn();
    }

    @Test(dataProvider = "register account data", dataProviderClass = ShoppingCartDataProvider.class)
    public void testRegisterAccount(String firstname, String lastname, String email, String password, String address, String city, String state, int zip, int phone) {

        SoftAssert softAssert = new SoftAssert();

        formEmail = email;
        formPassword = password;

        // 2. Register a new account

        signInPage.fillRegisterForm(formEmail);

        signUpPage = new SignUpPage(driver);
        signUpPage.fillRegisterForm(firstname, lastname, formPassword, address, city, zip, phone);

        myAccountPage = new MyAccountPage(driver);
        softAssert.assertTrue(myAccountPage.lblMyAccountIsDisplayed());

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"testRegisterAccount"}, dataProvider = "purchase process data", dataProviderClass = ShoppingCartDataProvider.class)
    public void testPurchasingProcess(String message) {

        SoftAssert softAssert = new SoftAssert();

        // 2. Sign in with an existing account
        signInPage.fillLoginForm(formEmail, formPassword);

        // 3. Enter the women tab
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickOnWomenTab();

        Object [][] shoopingCartProducts = new Object[MAX_PRODUCTS_CART][3];

        for (int i = 0; i < MAX_PRODUCTS_CART; i++) {
            categoryPage = new CategoryPage(driver);
            // 4. Enter the product detail
            categoryPage.mouseOverToProductItem(i);
            productPage = new ProductPage(driver);
            // 5. Print in console product description and price, change quantity product
            productPage.showProductInformation();
            productPage.changeProductQuantity();
            // 6. Check 4 images and change product size
            int sizeThumbProductImages = productPage.getSizeThumbProductImages();
            softAssert.assertEquals(sizeThumbProductImages, EXPECTED_SIZE_THUMB_PRODUCT_IMAGES);
            productPage.changeProductSize();
            // 7. Add product to shopping cart
            Object[] product = productPage.getDataProduct();
            for (int j = 0; j < product.length; j++) {
                shoopingCartProducts[i][j] = product[j];
            }

            productPage.addProductToCart(i, MAX_PRODUCTS_CART);
        }
        // 8. Repeat the process 2 times according to the loop

        // 9. Do click on check out
        productPage.goToCheckout();

        // 10. Check shopping cart details
        orderPage = new OrderPage(driver);

        Object [][] orderItems = orderPage.getDataProducts(MAX_PRODUCTS_CART);

        double expectedTotalProduct = Functions.refactorPrice(orderPage.getTextTotalProduct());
        double currentTotalProduct = 0;
        for (int i = 0; i < MAX_PRODUCTS_CART; i++) {
            double totalItem = Functions.refactorPrice(orderItems[i][1].toString()) * Integer.parseInt(orderItems[i][2].toString());
            currentTotalProduct = currentTotalProduct + totalItem;

            // 10.2 Verify that the unit price for each product is correct
            String expectedUnitPriceProduct = shoopingCartProducts[i][1].toString();
            String currentUnitPriceProduct = orderItems[i][1].toString();
            softAssert.assertEquals(currentUnitPriceProduct, expectedUnitPriceProduct);

            // 10.3 Verify that the quantity for each product is correct
            String expectedQuantityProduct = shoopingCartProducts[i][2].toString();
            String currentQuantityProduct = orderItems[i][2].toString();
            softAssert.assertEquals(currentQuantityProduct, expectedQuantityProduct);
        }

        // 10.1 Check if the page is correctly adding the prices of each product with the total products price
        softAssert.assertEquals(currentTotalProduct, expectedTotalProduct);

        // 10.4 Check that the total price is correct
        double expectedTotalPrice = Functions.refactorPrice(orderPage.getTextTotalPrice());
        double currentTotalPrice = orderPage.getPriceSum();
        softAssert.assertEquals(currentTotalPrice, expectedTotalPrice);

        // 10.5 Check product counter text in the upper right
        String currentTextProductCounter = orderPage.getTextProductCounter();
        softAssert.assertEquals(currentTextProductCounter, EXPECTED_TEXT_PRODUCT_COUNTER);

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
        softAssert.assertEquals(titleOrderConfirmation, EXPECTED_TEXT_ORDER_CONFIRMATION);

        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(){
        mainPage.clickOnSignOut();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
