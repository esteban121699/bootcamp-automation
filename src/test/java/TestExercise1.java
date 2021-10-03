import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestExercise1 extends Base {
    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void testForm() {
        // Set the value of the input type text
        driver.findElement(By.id("firstName")).sendKeys("Esteban Fernando");
        driver.findElement(By.id("lastName")).sendKeys("Castillo Camizán");
        driver.findElement(By.id("userEmail")).sendKeys("castilloesteban2009@gmail.com");

        // Set the value of the input type checkbox
        WebElement checkboxGender = driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label"));
        checkboxGender.click();

        // Set the value of the input type text
        driver.findElement(By.id("userNumber")).sendKeys("9643490470");

        // Set the value of the input type date
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.sendKeys(Keys.CONTROL + "a");
        dateOfBirthInput.sendKeys("12 May 1999");
        dateOfBirthInput.sendKeys(Keys.ENTER);

        // Set the value of the input type select
        WebElement selectSubject = driver.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]/div[2]"));
        selectSubject.click();
        WebElement inputSubject = driver.findElement(By.id("subjectsInput"));
        inputSubject.sendKeys("Computer Science");
        inputSubject.sendKeys(Keys.ENTER);

        // Set the value of the input type checkbox
        WebElement checkboxSport = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label"));
        checkboxSport.click();

        // Set the value of the input type text
        driver.findElement(By.id("currentAddress")).sendKeys("Villa el salvador");

        // Set the value of the input type select
        WebElement selectState = driver.findElement(By.xpath("//*[@id=\"state\"]/div/div[1]"));
        selectState.click();
        WebElement inputState = driver.findElement(By.id("react-select-3-input"));
        inputState.sendKeys("Haryana");
        inputState.sendKeys(Keys.ENTER);

        // Set the value of the input type select
        WebElement selectCity = driver.findElement(By.xpath("//*[@id=\"city\"]/div/div[1]"));
        selectCity.click();
        WebElement inputCity = driver.findElement(By.id("react-select-4-input"));
        inputCity.sendKeys("Karnal");
        inputCity.sendKeys(Keys.ENTER);

        // Click event to send form
        WebElement btnSubmit = driver.findElement(By.id("submit"));
        btnSubmit.click();

        // Get the title value of modal dialog
        String titleDialog = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();

        // Click event to close modal dialog
        WebElement btnCloseDialog = driver.findElement(By.id("closeLargeModal"));
        btnCloseDialog.click();

        // Compare obtained value and expected value
        String expectedTitle = "Thanks for submitting the form";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedTitle, titleDialog);
        softAssert.assertAll();
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
