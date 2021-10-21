package yelp.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestaurantPage extends PageObject {

    @FindBy(xpath="//h1")
    WebElementFacade tagListH1;

    @FindBy(xpath="//*[contains(text(),'Phone number')]//following-sibling::p")
    WebElementFacade lblRestaurantPhone;

    @FindBy(xpath="//*[contains(text(),'Get Directions')]//parent::p//following-sibling::p")
    WebElementFacade lblRestaurantAddress;

    @FindBy(xpath="//div[contains(@class,'i-stars__373c0___sZu0')]")
    List<WebElementFacade> lblRestaurantRating;

    public void getDetails() {
        String restaurantName = "The restaurant name is: " + tagListH1.getText();
        String restaurantPhone = "The restaurant phone is: " + lblRestaurantPhone.getText();
        String restaurantAddress = "The restaurant address is: " + lblRestaurantAddress.getText();
        String restaurantRating = "The restaurant rating is: " + lblRestaurantRating.get(0).getAttribute("aria-label");
        String details = restaurantName + "\n" + restaurantPhone + "\n" + restaurantAddress + "\n" + restaurantRating + "\n\n";

        String lblRestaurantReviewsXpath = "//p[@class='comment__373c0__Nsutg css-n6i4z7']";
        List<WebElementFacade> lblRestaurantReviews = withTimeoutOf(20, TimeUnit.SECONDS).findAll(By.xpath(lblRestaurantReviewsXpath));
        for (int i = 0; i < 3; i++) {
            details = details + "Restaurant review #"+ (i + 1) +": " + "\n" + lblRestaurantReviews.get(i).getText() + "\n";
        }

        Serenity.recordReportData().withTitle("Restaurant details and reviews").andContents(details);
    }
}
