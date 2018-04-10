package com.egt.qa.common.gui.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Class for the WebDriver work. It contains some useful methods for the work with the web-pages
 *
 * @author Ilia_Efimov1
 * @version 21 Aug 2017
 */
public class WebdriverHelper {
    /**
     * We have to return ImplicitlyWait to original position.
     */
    private static final long STANDART_WAIT = 20;
    private static final int ACCEPTABLE_PAUSE = 15;
    private static final String siteUrl = "https://www.gosuslugi.ru/";
    /**
     * JavaScript code to check if all the ajax requests completed
     */
    private static final String JS_AJAX_PROGRESS =
            "var userWindow = window;" +
                    "var docReady = window.document.readyState == 'complete';" +
                    "var isJqueryComplete = typeof(userWindow.jQuery) != 'function' || userWindow.jQuery.active == 0;" +
                    "var isPrototypeComplete = typeof(userWindow.Ajax) != 'function' || userWindow.Ajax.activeRequestCount == 0;" +
                    "var isDojoComplete = typeof(userWindow.dojo) != 'function' || userWindow.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
                    "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
                    "return result;";
    private static final ExpectedCondition<Object> isAJAXCompleted = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        assert js != null;
        return Boolean.parseBoolean(js.executeScript(JS_AJAX_PROGRESS).toString());
    };
    private final Logger log = LogManager.getLogger(this.getClass());

    /**
     * Open page with the specific <b>PAGE_URL</b>
     *
     * @param PAGE_URL suffix of the page url <i>(e.g. /messages)</i>
     */
    public void open(String PAGE_URL) {
        BaseSelenium.getSeleniumDriver().get(siteUrl + PAGE_URL);
    }

    /**
     * Checking the existing of the web-page element by path (e.g. xPath, css)
     *
     * @param element current web page element what can exists on our page or not.
     * @return boolean variable <i>TRUE</i> if element is really exists on this page
     */
    public boolean isExists(WebElement element) {
        BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            log.debug("Element '" + element + "' actually doesn't exists");
            return false;
        } catch (NullPointerException e) {
            return false;
        } finally {
            BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(STANDART_WAIT, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * Checking the existing of the web-page element by path (e.g. xPath, css)
     *
     * @param xPath xPath request
     * @return boolean variable <i>TRUE</i> if element is really exists on this page
     */
    public boolean isExists(String xPath) {
        BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            BaseSelenium.getSeleniumDriver().findElement(By.xpath(xPath)).isDisplayed();
            log.debug("Element '" + xPath + "' actually exists");
        } catch (NoSuchElementException e) {
            log.debug("Element '" + xPath + "' actually doesn't exists");
            return false;
        } finally {
            BaseSelenium.getSeleniumDriver().manage().timeouts().implicitlyWait(STANDART_WAIT, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * Check that page is opened.
     * Verify host, path, expected text on that page.
     *
     * @param PAGE_URL        suffix of the page url <i>(e.g. /messages)</i>
     * @param expectedText    the text what is expected on the <i>checkingElement</i>
     * @param checkingElement Web element with expected text (e.g. h1 title)
     */
    public void isOpen(String PAGE_URL, String expectedText, WebElement checkingElement) {
        Assert.assertEquals(BaseSelenium.getSeleniumDriver().getCurrentUrl(), siteUrl + PAGE_URL);
        log.warn(BaseSelenium.getSeleniumDriver().getCurrentUrl());
        Assert.assertTrue(isExists(checkingElement));
        Assert.assertEquals(checkingElement.getText(), expectedText);
    }

    /**
     * Clicling on the webelement
     *
     * @param webElement Current web-element (e.g. button or link)
     */
    public void click(WebElement webElement) {
        try {
            waitForPageUpdated();
            waitForElementToBeClickable(webElement);
            webElement.click();
            /*if (isExists(webElement)) {
                waitForElementToBeClickable(webElement);
                webElement.click();
            }*/
        } catch (Exception e) {
            log.error("There is some problem with clicking");
            //Assert.fail("There is some problem with clicking", e);
        } finally {
            log.debug("Clicked");
            waitForPageUpdated();
        }
    }

    /**
     * Sending keys into the input on the web-page
     *
     * @param webElement Current web-element (input)
     * @param inputText  What we have to insert
     */
    public void sendKeys(WebElement webElement, String inputText) {
        try {
            waitForPageUpdated();
            webElement.clear();
            webElement.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println("Exception in 'Send Keys'");
        }
    }

    /**
     * Check that Element has got a specific attribute
     *
     * @param element   WebElement that could have attribute
     * @param attribute specific attribute what we are seeking in this element
     * @return true if attribute exists in this element
     */
    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        String value = element.getAttribute(attribute);
        if (value != null) {
            result = true;
        }
        return result;
    }

    /**
     * Convert dropdown elements into the one string
     *
     * @param locator xPath locator
     * @return String with separated elements
     */
    public String getDropDownListAsString(By locator) {
        // TODO Java 8
        List<WebElement> allElements = BaseSelenium.getSeleniumDriver().findElements(locator);
        StringBuilder elementsString = new StringBuilder();
        String separator = ", ";

        for (WebElement element : allElements) {
            elementsString.append(element.getText()).append(separator);
        }
        elementsString.deleteCharAt(elementsString.length() - separator.length());

        return elementsString.toString();
    }

    /**
     * Get the special element from the dropdown menu
     *
     * @param xPath xPath request
     * @return WebElement object of this special element
     */
    public WebElement getElementFromXpath(String xPath) {
        WebElement elemFromDropD;
        Assert.assertTrue(isExists(elemFromDropD = BaseSelenium.getSeleniumDriver().findElement(By.xpath(xPath))));
        return elemFromDropD;
    }

    /**
     * Execute a JavaScript after the WebDriver js-capability check.
     *
     * @param script JavaScript string
     */
    public void executeJavaScript(String script) {
        JavascriptExecutor js;
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }

        js.executeScript(script);
    }

    /**
     * Waiting <b>ACCEPTABLE_PAUSE</b> for <i>element</i> clickability
     *
     * @param element Waiting this element to be clickable
     */
    private void waitForElementToBeClickable(WebElement element) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            new WebDriverWait(driver, ACCEPTABLE_PAUSE).until(ExpectedConditions.elementToBeClickable(element));
        } finally {
            log.debug("Waiting For Element To Be Clickable");
        }
    }

    /**
     * Waiting <b>ACCEPTABLE_PAUSE</b> for updating the webpage
     */
    private void waitForPageUpdated() {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, ACCEPTABLE_PAUSE, 20);
            wait.until(isAJAXCompleted);
        } finally {
            log.debug("Waiting for page update");
        }
    }

    /**
     * Pressing any key
     *
     * @param webElement webelemt (e.g. input field) that need to push the key (e.g. 'Enter' button)
     */
    public void pressKey(WebElement webElement, Keys key) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        try {
            waitForPageUpdated();
            if (isExists(webElement)) {
                waitForElementToBeClickable(webElement);
                (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div/div[1]/div[1]")));
                webElement.sendKeys(key);
            }
        } catch (Exception e) {
            System.out.println("Exception in 'Press Key'");
        }
    }

    public String getElementXPath(WebElement element) {
        WebDriver driver = BaseSelenium.getSeleniumDriver();
        return (String) ((JavascriptExecutor) driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
    }
}
