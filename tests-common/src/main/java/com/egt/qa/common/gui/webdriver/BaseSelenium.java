package com.egt.qa.common.gui.webdriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseSelenium {
    private static WebDriver driver;

    /**
     * Get the WebDriver
     *
     * @return driver from BaseSelenium
     */
    public static WebDriver getSeleniumDriver() {
        return driver;
    }

    /**
     * Установка браузера в зависимости от указанного названия
     *
     * @param browser Название браузера
     */
    public static void setup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            // Создать сущность Firefox
            FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            // Создать сущность Chrome
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("edge")) {
            // Создать сущность Edge
            EdgeDriverManager.getInstance(DriverManagerType.EDGE).setup();
            driver = new EdgeDriver();
        } else {
            // Если ни один браузер не одноружен
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void init() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void close() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver = null;
        }
    }
}