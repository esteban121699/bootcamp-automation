package com.pageobjects;

import com.automationpractice.utilities.Functions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//p[contains(@id, 'product_reference')]//span")
    private WebElement lblSku;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement btnAddProduct;

    @FindBy(xpath = "//ul[contains(@class, 'menu-content')]//a[@title='Women']")
    private WebElement btnWomen;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement btnContinueShopping;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement btnDisplayCart;

    @FindBy(xpath = "//a[@title='Check out']")
    private WebElement btnCheckout;

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
        waitClick.until(ExpectedConditions.visibilityOf(btnContinueShopping));
        btnContinueShopping.click();
        if(index < maxProductsCart - 1) {
            btnWomen.click();
        }
    }

    public void goToCheckout() {
        Actions action = new Actions(driver);
        action.moveToElement(btnDisplayCart).perform();
        WebDriverWait waitClick = new WebDriverWait(driver, 10);
        waitClick.until(ExpectedConditions.visibilityOf(btnCheckout));
        btnCheckout.click();
    }

    public int getSizeThumbProductImages() {
        return thumbProductImages.size();
    }

    public Object[] getDataProduct() {
        Object[] product = new Object[3];
        product[0] = lblSku.getText();
        product[1] = lblPrice.getText();
        product[2] = inputQuantity.getAttribute("value");
        return product;
    }
}
