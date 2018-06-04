import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JiraTest {
    WebDriver browser;
    String username = "i.zagorodniy";
    String password = "amarettoqwerty\n";

    @BeforeTest
    public void openChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/qa-1/Загрузки/chromedriver_linux64 (1)/chromedriver");
        browser = new ChromeDriver();
        browser.get("http://jira.hillel.it:8080/secure/Dashboard.jspa");
        sleep(1);

    }

//    @AfterTest
//    public void closeChrome() throws InterruptedException {
//        sleep(1);
//        browser.quit();
//    }


    public void sleep(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    @Test(priority = 1)
    public void logInJira() throws InterruptedException {
        browser.findElement(By.cssSelector("input[name=os_username]")).clear();
        browser.findElement(By.cssSelector("input[name=os_username]")).sendKeys(username);
        browser.findElement(By.cssSelector("input[name=os_password]")).clear();
        browser.findElement(By.cssSelector("input[name=os_password]")).sendKeys(password);
        sleep(5);
//        Assert.assertEquals(username, browser.findElement(By.cssSelector("img[alt=User profile for Igor Zagorodniy]")).
//                getText());
//        sleep(3);
    }

    @Test(priority = 2)
    public void createIssue () throws InterruptedException {
        browser.findElement(By.cssSelector("a[id=create_link]")).click();
        sleep(3);
        browser.findElement(By.cssSelector("input[id=summary]")).sendKeys("My first auto task");
        browser.findElement(By.cssSelector("input[id=create-issue-submit]")).click();


    }
}

