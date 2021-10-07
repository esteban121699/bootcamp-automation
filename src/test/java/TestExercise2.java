import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestExercise2 extends Base {
    @BeforeTest(alwaysRun = true)
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://demoqa.com/buttons");
    }

    @Test(groups = {"allButtons"})
    public void testButtons() {
        /*************** Initialize action and soft assert ****************/

        Actions action = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();

        /********************** Double Click Button ***********************/

        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        action.doubleClick(doubleClickBtn).perform();

        WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));

        WebDriverWait waitDoubleClick = new WebDriverWait(driver, 5);
        waitDoubleClick.until(ExpectedConditions.visibilityOf(doubleClickMessage));

        String currentTextDoubleClick = doubleClickMessage.getText();
        String expectedTextDoubleClick = "You have done a double click";

        softAssert.assertEquals(currentTextDoubleClick, expectedTextDoubleClick);

        /********************** Right Click Button ***********************/

        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        action.contextClick(rightClickBtn).perform();

        WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));

        WebDriverWait waitRightClick = new WebDriverWait(driver, 5);
        waitRightClick.until(ExpectedConditions.visibilityOf(rightClickMessage));

        String currentTextRightClick = rightClickMessage.getText();
        String expectedTextRightClick = "You have done a right click";

        softAssert.assertEquals(currentTextRightClick, expectedTextRightClick);

        /*************************** Click Button ***********************/

        WebElement clickBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
        clickBtn.click();

        WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));

        WebDriverWait waitClick = new WebDriverWait(driver, 5);
        waitClick.until(ExpectedConditions.visibilityOf(dynamicClickMessage));

        String currentTextClick = dynamicClickMessage.getText();
        String expectedTextClick = "You have done a dynamic click";

        softAssert.assertEquals(currentTextClick, expectedTextClick);

        /*************************** Do assert all ***********************/

        softAssert.assertAll();
    }

    @Test(groups = {"oneButton"})
    public void testDoubleClickBtn() {
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));

        Actions action = new Actions(driver);
        action.doubleClick(doubleClickBtn).perform();

        WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(doubleClickMessage));

        String currentText = doubleClickMessage.getText();
        String expectedText = "You have done a double click";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedText, currentText);
        softAssert.assertAll();
    }

    @Test(groups = {"oneButton"})
    public void testRightClickBtn() {
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));

        Actions action = new Actions(driver);
        action.contextClick(rightClickBtn).perform();

        WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(rightClickMessage));

        String currentText = rightClickMessage.getText();
        String expectedText = "You have done a right click";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedText, currentText);
        softAssert.assertAll();
    }

    @Test(groups = {"oneButton"})
    public void testClickBtn() {
        WebElement clickBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
        clickBtn.click();

        WebElement dynamicClickMessage = driver.findElement(By.id("dynamicClickMessage"));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(dynamicClickMessage));

        String currentText = dynamicClickMessage.getText();
        String expectedText = "You have done a dynamic click";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedText, currentText);
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        driver.close();
    }
}
