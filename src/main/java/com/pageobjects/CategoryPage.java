package com.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage {
    public final WebDriver driver;

    @FindBy(xpath = "//ul[contains(@class, 'product_list')]")
    private WebElement productList;

    @FindBy(xpath = "//ul[contains(@class, 'product_list')]//li[contains(@class, 'ajax_block_product')]")
    private List<WebElement> productItems;

    @FindBy(xpath = "//div[contains(@class, 'button-container')]//a[contains(@class, 'lnk_view ')]")
    private List<WebElement> btnAddProductItems;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productList);
    }

    public void mouseOverToProductItem(int index) {
        Actions action = new Actions(driver);
        action.moveToElement(productItems.get(index)).perform();
        btnAddProductItems.get(index).click();
    }
}
