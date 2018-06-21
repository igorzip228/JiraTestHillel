import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class JiraTest {
    static WebDriver browser;
    String username = "i.zagorodniy";
    String password = "amarettoqwerty\n";
    String nameIssue = "My second issue";
    static String newIssuePath;

    @BeforeTest
    public void openChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/qa-1/IdeaProjects/chromedriver");
        browser = new ChromeDriver();
        browser.get("http://jira.hillel.it:8080/secure/Dashboard.jspa");
        browser.manage().window().maximize(); // разворачиваем на весь экран
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

    @Test(description = "Login Jira", priority = 1; )
    public void logInJira() throws InterruptedException {
        clearAndFill(By.cssSelector("input[name=os_username]"), username);
        clearAndFill(By.cssSelector("input[name=os_password]"), password);
        sleep(5);
        Assert.assertEquals("Create", browser.findElement(By.cssSelector("a[id=create_link]")).getText());
        sleep(3);
    }

    @Test(description = "Create issue", priority = 2)
    public void createIssue () throws InterruptedException {
        browser.findElement(By.cssSelector("a[id=create_link]")).click();
        sleep(3);
        clearAndFill(By.cssSelector("input[id=summary]"), nameIssue);
        browser.findElement(By.cssSelector("input[id=create-issue-submit]")).click();
        sleep(2);

        browser.findElement(By.cssSelector("a[id='find_link']")).click();
        sleep(2);
        browser.findElement(By.cssSelector("a[id='filter_lnk_reported_lnk']")).click();
        sleep(2);
//        WebElement selectElem = browser.findElement(By.cssSelector("a[id='find_link']"));
//        Select select = new Select(selectElem);
//        List options = select.getOptions();
//        List selectedOptions = select.getAllSelectedOptions();
//        select.selectByVisibleText("Reported by me");
        List<WebElement> newIssueLinks = browser.findElements(By.cssSelector("a.splitview-issue-link")); //где взялся селектор???? - общая ссылка для всех элементов со списка
        Assert.assertTrue(newIssueLinks.size() != 0);

        newIssuePath = newIssueLinks.get(0).getAttribute("href"); // что такое getAttribute

    }

    @Test(description = "Open issue", priority = 3)
    private void openTicket(){
        browser.get(newIssuePath);
        Assert.assertTrue(browser.getTitle().contains(nameIssue));// что такое getTitle()
    }


    private static WebElement clearAndFill(By selector, String data) {
       WebElement element = browser.findElement(selector);
        // WebElement element = browser.findElement(selector);
        element.clear();
        element.sendKeys(data);

        return element;
    }
}

