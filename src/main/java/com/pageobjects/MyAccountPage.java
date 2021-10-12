package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    public final WebDriver driver;

    @FindBy(xpath = "//span[@class='navigation_page' and contains(.,'My account')]")
    private WebElement lblMyAccount;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement tabWomen;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean lblMyAccountIsDisplayed() {
        return lblMyAccount.isDisplayed();
    }

    public void clickOnWomenTab() {
        tabWomen.click();
    }
}
