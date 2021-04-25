package cbatest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    static WebDriver driver;

    @BeforeClass
    public static void testLaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_search/862b0faa506b8487c25a3384cfde8af4/static/attachments/reference_page.html");
    }

    @Test
    public void test_queryInputAndSearchButton() {
        Assert.assertTrue(driver.findElement(SearchPage.searchInputBox).isDisplayed());
        Assert.assertTrue(driver.findElement(SearchPage.searchButton).isDisplayed());
    }

    @Test
    public void test_emptyQuery() {
        driver.findElement(SearchPage.searchInputBox).clear();
        driver.findElement(SearchPage.searchButton).click();
        String errormsg = driver.findElement(SearchPage.errorMessage).getText();

        Assert.assertEquals("The error Message is", "Provide some query", errormsg);
    }

    @Test
    public void test_queryForIsla() {
        driver.findElement(SearchPage.searchInputBox).clear();
        driver.findElement(SearchPage.searchInputBox).sendKeys("isla");
        driver.findElement(SearchPage.searchButton).click();

        Assert.assertTrue(driver.findElement(SearchPage.resultListItem).isDisplayed());
        int listSize = driver.findElements(SearchPage.resultListItem).size();

        Assert.assertTrue("No of results Displayed for Isle is greater than 1", listSize >= 1);
        System.out.println("No of results Displayed for Isle : " + listSize);
    }

    @Test
    public void test_queryForCastle() {
        driver.findElement(SearchPage.searchInputBox).clear();
        driver.findElement(SearchPage.searchInputBox).sendKeys("castle");
        driver.findElement(SearchPage.searchButton).click();
        String errormsg = driver.findElement(SearchPage.noResult).getText();

        Assert.assertEquals("The error Message is", "No results", errormsg);
    }

    @Test
    public void test_queryForPort() {
        driver.findElement(SearchPage.searchInputBox).clear();
        driver.findElement(SearchPage.searchInputBox).sendKeys("port");
        driver.findElement(SearchPage.searchButton).click();
        int listSize = driver.findElements(SearchPage.resultListItem).size();
        Assert.assertTrue("No of results Displayed for Port is 1", listSize == 1);
        System.out.println("No of results Displayed for Port is : " + listSize);
        String portResult = driver.findElement(SearchPage.resultListItem).getText();

        Assert.assertEquals("The Search Result for port is", "Port Royal", portResult);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

}
