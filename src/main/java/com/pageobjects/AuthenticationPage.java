package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {
    private final WebDriver driver;

    @FindBy(id = "email")
    private WebElement inputSignInEmail;

    @FindBy(id = "passwd")
    private WebElement inputSignInPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnSignInSubmit;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillLoginForm(String email, String password) {
        inputSignInEmail.sendKeys(email);
        inputSignInPassword.sendKeys(password);

        btnSignInSubmit.click();
    }
}
