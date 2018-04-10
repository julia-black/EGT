package com.egt.qa.common.gui.webdriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public static void init() {
        ChromeDriverManager.getInstance().setup();
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