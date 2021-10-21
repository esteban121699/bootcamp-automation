package yelp.pageobjects;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {
    @FindBy(xpath="//a[contains(text(),'Restaurants')]")
    WebElementFacade btnRestaurants;

    public void goToRestaurants() {
        btnRestaurants.click();
    }
}
