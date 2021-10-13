package com.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.automationpractice.utilities.Functions.refactorPrice;

public class OrderPage {
    public final WebDriver driver;

    @FindBy(xpath = "//p[contains(@class, 'cart_navigation')]//a[@title='Proceed to checkout']")
    private WebElement btnProceedToCheckoutStep1;

    @FindBy(xpath = "//button[@name='processAddress']")
    private WebElement btnProceedToCheckoutStep3;

    @FindBy(xpath = "//button[@name='processCarrier']")
    private WebElement btnProceedToCheckoutStep4;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement inputMessage;

    @FindBy(xpath = "//a[@title='Pay by bank wire']")
    private WebElement btnPayByBank;

    @FindBy(xpath = "//button[contains(.,'I confirm my order')]")
    private WebElement btnConfirmOrder;

    @FindBy(xpath = "//p[contains(@class, 'cheque-indent')]//strong[contains(@class, 'dark')]")
    private WebElement lblOrderConfirmation;

    @FindBy(xpath = "//h1[contains(@id, 'cart_title')]//span[contains(@class, 'heading-counter')]")
    private WebElement lblProductCounter;

    @FindBy(xpath = "//td[contains(@class, 'cart_description')]//small[contains(@class, 'cart_ref')]")
    private List<WebElement> lblProductSkus;

    @FindBy(xpath = "//td[contains(@class, 'cart_unit')]//span[contains(@class, 'price')]//span[contains(@class, 'price')]")
    private List<WebElement> lblProductUnitPrices;

    @FindBy(xpath = "//td[contains(@class, 'cart_quantity ')]//input[contains(@class, 'cart_quantity_input ')]")
    private List<WebElement> inputProductQuantity;

    @FindBy(xpath = "//td[contains(@class, 'cart_total')]//span[contains(@class, 'price')]")
    private List<WebElement> lblProductTotalPrices;

    @FindBy(id = "uniform-cgv")
    private WebElement checkboxTerms;

    @FindBy(id = "total_product")
    private WebElement lblTotalProduct;

    @FindBy(id = "total_shipping")
    private WebElement lblTotalShipping;

    @FindBy(id = "total_tax")
    private WebElement lblTotalTax;

    @FindBy(id = "total_price")
    private WebElement lblTotalPrice;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToProceedToCheckout() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnProceedToCheckoutStep1);
        btnProceedToCheckoutStep1.click();
    }

    public void fillForm(String message) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputMessage);
        inputMessage.sendKeys(message);
        btnProceedToCheckoutStep3.click();
    }

    public void acceptTermsAndConditions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkboxTerms);
        checkboxTerms.click();
        btnProceedToCheckoutStep4.click();
    }

    public void chooseMethodPayment() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnPayByBank);
        btnPayByBank.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnConfirmOrder);
        btnConfirmOrder.click();
    }

    public double getPriceSum() {
        double totalProduct = refactorPrice(lblTotalProduct.getText());
        double totalShipping = refactorPrice(lblTotalShipping.getText());
        double totalTax = refactorPrice(lblTotalTax.getText());
        double totalPrice = totalProduct + totalShipping + totalTax;
        return totalPrice;
    }

    public String getTextOrderConfirmation() {
        return lblOrderConfirmation.getText();
    }

    public String getTextTotalProduct() {
        return lblTotalProduct.getText();
    }

    public String getTextProductCounter() {
        return lblProductCounter.getText();
    }

    public String getTextTotalPrice() {
        return lblTotalPrice.getText();
    }

    public Object[][] getDataProducts(int maxProductsCart) {
        Object[][] products = new Object[maxProductsCart][4];
        for (int i = 0; i < maxProductsCart; i++) {
            products[i][0] = lblProductSkus.get(i).getText();
            products[i][1] = lblProductUnitPrices.get(i).getText();
            products[i][2] = inputProductQuantity.get(i).getAttribute("value");
            products[i][3] = lblProductTotalPrices.get(i).getText();
        }
        return products;
    }
}
