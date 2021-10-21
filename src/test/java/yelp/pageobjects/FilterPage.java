package yelp.pageobjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FilterPage extends PageObject {
    @FindBy(xpath = "//input[@id='search_description']")
    WebElementFacade inputSearchDescription;

    @FindBy(xpath="//form[@id='header_find_form']//button[@value='submit']")
    WebElementFacade btnSearchDescription;

    @FindBy(xpath="//div[contains(@class,'pagination__09f24__3EI7A')]//span[contains(@class,'css-e81eai')]")
    WebElementFacade lblPageOf;

    static String lblRestaurantName="//*[contains(@class,'ResultsContainer')]//descendant::h4";

    static List<WebElementFacade> listRestaurants;

    public void searchGeneralDescription(String word) {
        inputSearchDescription.clear();
        inputSearchDescription.sendKeys(word);
        btnSearchDescription.click();
        getNumberResultsPerPage();
    }

    public void chooseFilterDistance(String distance) {
        String inputRadioFilterXpath = "//span[contains(text(),'"+ distance +"')]//ancestor::label";
        WebElement inputRadioFilter = withTimeoutOf(20, TimeUnit.SECONDS).find((By.xpath(inputRadioFilterXpath)));
        inputRadioFilter.click();
    }

    public void selectFirstResult(){
        listRestaurants = withTimeoutOf(20, TimeUnit.SECONDS).findAll(By.xpath(lblRestaurantName));
        for(WebElementFacade inputElement : listRestaurants){
            if(inputElement.getText().contains(".")){
                inputElement.click();
                break;
            }
        }
    }

    public void getNumberResultsPerPage() {
        int countFirstPage = 0;
        listRestaurants = withTimeoutOf(20, TimeUnit.SECONDS).findAll(By.xpath(lblRestaurantName));
        for(WebElementFacade inputElement : listRestaurants){
            if(inputElement.getText().contains(".")){
                countFirstPage = countFirstPage + 1;
            }
        }

        String[] splitStrLblPageOf = lblPageOf.getText().split("\\s+");
        int lastPage = Integer.parseInt(splitStrLblPageOf[splitStrLblPageOf.length - 1]);

        for (int i = 0; i < lastPage; i++) {
            String btnPageXpath = "//div[contains(@aria-label, 'Page: " + (i + 1) + "')]";
            WebElement btnPage = withTimeoutOf(30, TimeUnit.SECONDS).find((By.xpath(btnPageXpath)));
            btnPage.click();
        }

        int countLastPage = 0;
        listRestaurants = withTimeoutOf(20, TimeUnit.SECONDS).findAll(By.xpath(lblRestaurantName));
        for(WebElementFacade inputElement : listRestaurants){
            if(inputElement.getText().contains(".")){
                countLastPage = countLastPage + 1;
            }
        }

        String totalRecordsCurrentPage = "The total records of the current page is: " + countFirstPage;
        int calculateRecords = countLastPage + ((lastPage - 1) * countFirstPage);
        String totalRecordsAllPage = "The total records on all pages is: " + calculateRecords;

        String records = totalRecordsAllPage + "\n" + totalRecordsCurrentPage;

        Serenity.recordReportData().withTitle("Total Records").andContents(records);
    }
}
