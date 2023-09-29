package ru.csap.cibersportanalisysproject.game.attributes.servises;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConnectionServise {

    private static final ChromeDriver chromeDriver = new UndetectedChromeDriver().getUndetectedChromeDriver();;
    public static Document connection(String url)
    {
        chromeDriver.get(url);
        return Jsoup.parse(chromeDriver.getPageSource());
    }
    public ChromeDriver getDriver() {
        return chromeDriver;
    }
    public void close(){
        chromeDriver.close();
    }
}
