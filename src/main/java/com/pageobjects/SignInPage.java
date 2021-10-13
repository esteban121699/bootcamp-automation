package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    private final WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement inputSignUpEmail;

    @FindBy(id = "SubmitCreate")
    private WebElement btnSignUpSubmit;

    @FindBy(id = "email")
    private WebElement inputSignInEmail;

    @FindBy(id = "passwd")
    private WebElement inputSignInPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnSignInSubmit;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillLoginForm(String email, String password) {
        inputSignInEmail.sendKeys(email);
        inputSignInPassword.sendKeys(password);

        btnSignInSubmit.click();
    }

    public void fillRegisterForm(String email) {
        inputSignUpEmail.sendKeys(email);

        btnSignUpSubmit.click();
    }
}
