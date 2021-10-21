package yelp.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import yelp.steps.YelpUser;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class StepDefinitionUser {
    @Steps(shared=true)
    YelpUser yelpUser;

    @Given("^user navigates to https://www.yelp.com/$")
    public void userNavigateToYelp() {
        yelpUser.navigatesTo();
    }

    @And("^selects restaurants$")
    public void userGoToRestaurants() {
        yelpUser.goToRestaurants();
    }

    @Given("^user searches by (.*)$")
    public void userSearchGeneralDescription(String word) {
        yelpUser.searchGeneralDescription(word);
    }

    @When("^filters by (.*)$")
    public void userFilterByDistance(String distance) {
        yelpUser.chooseFilterDistance(distance);
    }

    @And("^selects the first result$")
    public void userSelectFirstResult() {
        yelpUser.selectFirstResult();
    }

    @Then("^views restaurant information$")
    public void userViewFirstResult() {
        yelpUser.viewFirstResult();
    }
}
