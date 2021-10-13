package com.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private final WebDriver driver;

    @FindBy(id = "id_gender1")
    private WebElement checkboxGender;

    @FindBy(id = "customer_firstname")
    private WebElement inputFirstname;

    @FindBy(id = "customer_lastname")
    private WebElement inputLastname;

    @FindBy(id = "passwd")
    private WebElement inputPassword;

    @FindBy(id = "days")
    private WebElement selectDaysBirth;

    @FindBy(id = "months")
    private WebElement selectMonthsBirth;

    @FindBy(id = "years")
    private WebElement selectYearsBirth;

    @FindBy(id = "address1")
    private WebElement inputAddress;

    @FindBy(id = "city")
    private WebElement inputCity;

    @FindBy(id = "id_state")
    private WebElement selectStateAddress;

    @FindBy(id = "postcode")
    private WebElement inputZip;

    @FindBy(id = "phone_mobile")
    private WebElement inputPhone;

    @FindBy(id = "submitAccount")
    private WebElement btnSignUpSubmit;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillRegisterForm(String firstname, String lastname, String password, String address, String city, int zip, int phone) {
        WebDriverWait waitClick = new WebDriverWait(driver, 10);
        waitClick.until(ExpectedConditions.visibilityOf(checkboxGender));
        checkboxGender.click();
        inputFirstname.sendKeys(firstname);
        inputLastname.sendKeys(lastname);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputLastname);
        inputPassword.sendKeys(password);
        Select selectDays = new Select(selectDaysBirth);
        selectDays.selectByValue("12");
        Select selectMonths = new Select(selectMonthsBirth);
        selectMonths.selectByValue("5");
        Select selectYears = new Select(selectYearsBirth);
        selectYears.selectByValue("1999");
        inputAddress.sendKeys(address);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputAddress);
        inputCity.sendKeys(city);
        Select selectState = new Select(selectStateAddress);
        selectState.selectByVisibleText("Alaska");
        inputZip.sendKeys(String.valueOf(zip));
        inputPhone.sendKeys(String.valueOf(phone));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSignUpSubmit);
        btnSignUpSubmit.click();
    }
}
