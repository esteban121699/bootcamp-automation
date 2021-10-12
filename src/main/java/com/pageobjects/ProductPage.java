package com.pageobjects;

import com.automationpractice.utilities.Functions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {
    public final WebDriver driver;

    @FindBy(id = "short_description_content")
    private WebElement lblDescription;

    @FindBy(id = "our_price_display")
    private WebElement lblPrice;

    @FindBy(id = "quantity_wanted")
    private WebElement inputQuantity;

    @FindBy(id = "group_1")
    private WebElement selectSize;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement btnAddProduct;

    @FindBy(xpath = "//ul[contains(@class, 'menu-content')]//a[@title='Women']")
    private WebElement btnWomen;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement btnContinueShopping;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement btnProceedCheckout;

    @FindBy(xpath = "//ul[contains(@id, 'thumbs_list_frame')]//li")
    private List<WebElement> thumbProductImages;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void showProductInformation() {
        System.out.println("PRODUCT DESCRIPTION: " + lblDescription.getText());
        System.out.println("PRODUCT PRICE: " + lblPrice.getText());
    }

    public void changeProductQuantity() {
        inputQuantity.clear();
        inputQuantity.sendKeys(Functions.randomQuantityProduct());
    }

    public void changeProductSize() {
        Select selectProductSize = new Select(selectSize);
        selectProductSize.selectByVisibleText("L");
    }

    public void addProductToCart(int index, int maxProductsCart) {
        btnAddProduct.click();
        WebDriverWait waitClick = new WebDriverWait(driver, 10);
        if(index < maxProductsCart - 1) {
            waitClick.until(ExpectedConditions.visibilityOf(btnContinueShopping));
            btnContinueShopping.click();
            btnWomen.click();
        } else {
            waitClick.until(ExpectedConditions.visibilityOf(btnProceedCheckout));
            btnProceedCheckout.click();
        }
    }

    public int sizeThumbProductImages() {
        return thumbProductImages.size();
    }
}
