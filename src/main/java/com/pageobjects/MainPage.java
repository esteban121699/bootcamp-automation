package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public final WebDriver driver;

    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    private WebElement btnSignIn;

    @FindBy(xpath = "//a[@title='Log me out']")
    private WebElement btnSignOut;

    public final String url = "http://automationpractice.com/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return url;
    }

    public void clickOnSignIn() {
        btnSignIn.click();
    }

    public void clickOnSignOut() {
        btnSignOut.click();
    }
}
