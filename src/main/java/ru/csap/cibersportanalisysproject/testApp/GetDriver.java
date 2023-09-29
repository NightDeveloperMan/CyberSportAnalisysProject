package ru.csap.cibersportanalisysproject.testApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GetDriver extends ChromeDriver {

    private WebDriver driver ;
    public WebDriver newDriver()
    {
        ChromeDriver chromeDriver = new ChromeDriver();
        driver = chromeDriver;
        return driver;
    }

    public void allowHltvCookies()
    {
        WebElement button = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        button.click();
    }


}
