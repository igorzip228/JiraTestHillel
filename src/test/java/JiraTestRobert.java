import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JiraTestRobert {
    static WebDriver browser;

    static String baseURL = "http://jira.hillel.it:8080/";
    static String username = "i.zagorodniy";
    static String password = "amarettoqwerty\n";

    static String newIssueSummary = "AutoTest" + new SimpleDateFormat("yyyy.MM.dd.MW.mm.ss").format(new Date());
    static String newIssuePath;

    @BeforeTest
    protected static void setUp(){

        System.setProperty("webdriver.chrome.driver", "/home/qa-1/Загрузки/chromedriver_linux64 (1)/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        browser = new ChromeDriver(options);
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.get(baseURL);
    }

    @Test(description = "Valid Login")
    private void login() {
        clearAndFill(By.cssSelector("input#login-form-username"), username);
        clearAndFill(By.cssSelector("input#login-form-username"), password).submit();

        Assert.assertEquals(username,|
        browser.findElement(By.cssSelector("a#header-details-fullname")).getAttribute("data-username"));
    }

}
