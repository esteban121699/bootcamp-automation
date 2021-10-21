package yelp.steps;

import yelp.pageobjects.FilterPage;
import yelp.pageobjects.MainPage;
import net.thucydides.core.annotations.Steps;
import yelp.pageobjects.RestaurantPage;

public class YelpUser {
    @Steps(shared=true)
    MainPage mainPage;
    FilterPage filterPage;
    RestaurantPage restaurantPage;

    public void navigatesTo(){
        mainPage.setDefaultBaseUrl("https://www.yelp.com/");
        mainPage.open();
    }

    public void goToRestaurants() {
        mainPage.goToRestaurants();
    }

    public void searchGeneralDescription(String word){
        filterPage.searchGeneralDescription(word);
    }

    public void chooseFilterDistance(String distance){
        filterPage.chooseFilterDistance(distance);
    }

    public void selectFirstResult() {
        filterPage.selectFirstResult();
    }

    public void viewFirstResult() {
        restaurantPage.getDetails();
    }
}
