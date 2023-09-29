package ru.csap.cibersportanalisysproject.game.attributes.servises;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UndetectedChromeDriver {

    public ChromeDriver getUndetectedChromeDriver()
    {
        Map<String, Object> prefs = new HashMap<>(); // Отключение атрибутов сайта для увеличение скорости загрузки html-страницы
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("profile.managed_default_content_settings.mixed_script", 2);
        prefs.put("profile.managed_default_content_settings.media_stream", 2);
        prefs.put("profile.managed_default_content_settings.stylesheets", 2);
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/Program Files (x86)/Google/chromedriver-win64/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        //___________________________________________________________________________________________
        options.setExperimentalOption("prefs", prefs);
        ChromeDriver chromeDriver = new ChromeDriver(service, options);
        chromeDriver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", Map.of(
                "source", "delete window.cdc_adoQpoasnfa76pfcZLmcfl_JSON;" +
                        "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Array;" +
                        "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Object;" +
                        "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Promise;" +
                        "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Proxy;" +
                        "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Symbol;"
        ));                                                                        // иммитация работы человека в браузере
        //_____________________________________________________________________________________________
        return chromeDriver;
//        WebElement button = chromeDriver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
//        button.click();

    }

}
