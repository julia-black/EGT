package com.egt.qa.common.gui.pages;

import com.egt.qa.common.gui.webdriver.BaseSelenium;
import com.egt.qa.common.gui.webdriver.WebdriverHelper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract class for Page Objects. It contains some useful methods for the work with the web-pages on the EGT site
 *
 * @author Ilia_Efimov1
 * @version 21 Aug 2017
 */
public abstract class AbstractPageObject {
    protected final WebdriverHelper webdriverHelper = new WebdriverHelper();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//span[@class='ng-binding ng-scope']")
    private WebElement btnNickname;
    @FindBy(xpath = ".//*[contains(@class,'logout')]")
    private WebElement btnLogout;
    @FindBy(xpath = ".//*[@id=\"page-wrap\"]//div[@class='epgu-breadcrumbs content-center-box']/ul/li[2]")
    private WebElement lblActiveMenu;
    @FindBy(xpath = ".//*[@id=\"page-wrap\"]//div[@class='epgu-breadcrumbs content-center-box']/ul/li[3]")
    private WebElement lblActiveSubMenu;

    //</editor-folder>

    protected AbstractPageObject() {
        PageFactory.initElements(BaseSelenium.getSeleniumDriver(), this);
    }

    protected abstract String getPAGE_URL();
    protected abstract String getPAGE_NAME();

    public abstract void isOpen();

    //<editor-folder desc="Opening">

    /**
     * Open page with the specific <b>PAGE_URL</b>
     */
    public void open() {
        log.info("Opening the " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    /**
     * Check that page is opened.
     * Verify host, path, expected text on that page.
     *
     * @param expectedText    the text what is expected on the <i>checkingElement</i>
     * @param checkingElement Web element with expected text (e.g. h1 title)
     */
    protected void isOpen(String expectedText, WebElement checkingElement) {
        log.info("Checking that webelement even exists and equals to '" + expectedText + "'");
        webdriverHelper.isOpen(getPAGE_URL(), expectedText, checkingElement);
    }

    /**
     * Check that page is opened.
     * Verify that active submenu (label) actually exists and equal to expected strings.
     *
     * @param expectedText the text what is expected on the <i>lblActiveSubMenu</i>
     */
    protected void isOpen(String expectedText) {
        log.info("Checking that the active submenu on the horizontal bar even exists and is equal to '"
                + expectedText + "'");
        webdriverHelper.isOpen(getPAGE_URL(), expectedText, lblActiveSubMenu);
    }

    /**
     * Check that page is opened.
     * Verify that active menu (button) and active submenu (label) actually exists and equal to expected strings.
     *
     * @param expectedTextSubMenu the text what is expected on the <i>lblActiveSubMenu</i>
     * @param expectedTextMenu    the text what is expected on the <i>btnActiveMenu</i>
     */
    protected void isOpen(String expectedTextSubMenu, String expectedTextMenu) {
        log.info("Checking that the active menu button on the horizontal bar even exists and is equal to '"
                + expectedTextMenu + "'");
        webdriverHelper.isOpen(getPAGE_URL(), expectedTextMenu, lblActiveMenu);
        log.info("Checking that the active submenu on the horizontal bar even exists and is equal to '"
                + expectedTextSubMenu + "'");
        webdriverHelper.isOpen(getPAGE_URL(), expectedTextSubMenu, lblActiveSubMenu);
    }

    //</editor-folder>

    //<editor-folder desc="Logging">

    /**
     * Check that user had authorized into the system
     */
    public void isLogin(String login) {
        isOpen(login, btnNickname);
    }

    public boolean isLoginWithoutAssert() {
        return isExists(btnNickname, "ФИО пользователя");
    }

    /**
     * Logging out
     */
    public void logOut() {
        log.info("Logging out from " + getPAGE_NAME());
        click(btnNickname, "кнопка 'ФИО пользователя'");
        click(btnLogout, "кнопка ");
    }

    //</editor-folder>

    /**
     * Checking the existing of the web-page element by path (e.g. xPath, css)
     *
     * @param element  current web page element what can exists on our page or not.
     * @param elemName name of the current element
     * @return boolean variable <i>TRUE</i> if element is really exists on this page
     */
    protected boolean isExists(WebElement element, String elemName) {
        log.info("Checking that " + elemName + " exists on the " + getPAGE_NAME());
        return webdriverHelper.isExists(element);
    }

    /**
     * Checking the existing of the web-page element by path (e.g. xPath, css)
     *
     * @param elementXPath xPath request for the current web page element what can exists on our page or not.
     * @param elemName     name of the current element
     * @return boolean variable <i>TRUE</i> if element is really exists on this page
     */
    protected boolean isExists(String elementXPath, String elemName) {
        log.info("Checking that " + elemName + " exists on the " + getPAGE_NAME());
        return webdriverHelper.isExists(elementXPath);
    }

    /**
     * Clicling on the webelement
     *
     * @param webElement Current web-element (e.g. button or link)
     */
    protected void click(WebElement webElement, String webElemNameForLog) {
        webdriverHelper.isExists(webElement);
        log.info("Нажимается " + webElemNameForLog);
        webdriverHelper.click(webElement);
    }

    /**
     * Sending keys into the input on the web-page
     *
     * @param webElement Current web-element (input)
     * @param inputText  What we have to insert
     */
    protected void sendKeys(WebElement webElement, String inputText, String inputName) {
        log.info("Sending keys into the " + inputName + " on the " + getPAGE_NAME() + ".\tKeys: " + inputText);
        webdriverHelper.sendKeys(webElement, inputText);
    }

    /**
     * Check that input can't fit more characters number than the limit.
     *
     * @param inputField Containing entered text field
     * @param limit      The max border of symbol's count
     * @return False if symbol's count is not pushing the limit
     */
    protected boolean isCountBiggerThanLimit(WebElement inputField, int limit, String inputName) {
        log.info("Checking that input " + inputName + " can't fit more characters number than the limit on the "
                + getPAGE_NAME());
        return (inputField.getText().length() > limit);
    }

    /**
     * Check that Element has got a specific attribute
     *
     * @param element   WebElement that could have attribute
     * @param attribute specific attribute what we are seeking in this element
     * @return true if attribute exists in this element
     */
    protected boolean isAttributePresent(WebElement element, String attribute) {
        log.info("Checking that " + element.getTagName() + " has an " + attribute + " on the "
                + getPAGE_NAME());
        return webdriverHelper.isAttribtuePresent(element, attribute);
    }

    //<editor-folder desc="Dropdown">

    /**
     * Convert dropdown elements into the one string
     *
     * @param locator          xPath locator
     * @param dropdownMenuName name of the current dropdown menu
     * @return String with separated elements
     */
    protected String getDropDownListAsString(By locator, String dropdownMenuName) {
        log.info("Converting dropdown elements from " + dropdownMenuName + " into the one string on the "
                + getPAGE_NAME());
        return webdriverHelper.getDropDownListAsString(locator);
    }

    /**
     * Get the special element from the dropdown menu with class <i>listContainer</i>
     *
     * @param elementToSelect  name of the element in the xPath
     * @param dropdownMenuName name of the current dropdown menu
     * @return WebElement object of this special element
     */
    private WebElement getElementFromDropdown(String elementToSelect, String dropdownMenuName) {
        log.info("Getting the special element " + elementToSelect + " from " + dropdownMenuName + " on the "
                + getPAGE_NAME());
        return webdriverHelper.getElementFromXpath
                (".//div[@title='" + elementToSelect + "']");
        //(".//*[@class='listContainer']/*[@title='" + elementToSelect + "']");
    }

    /**
     * Get the special element from the universal EGT dropdown menu
     *
     * @param elementToSelect  name of the element in the xPath
     * @param id               id of this dropdown menu
     * @param dropdownMenuName name of the current dropdown menu
     * @return WebElement object of this special element
     */
    protected WebElement getElementFromDropdown(String elementToSelect, String id, String dropdownMenuName) {
        log.info("Getting the special element " + elementToSelect + " from " + dropdownMenuName + " on the "
                + getPAGE_NAME());
        return webdriverHelper.getElementFromXpath
                (".//*[@id='" + id + "']//*[@class='listContainer']/*[@title='" + elementToSelect + "']");
    }

    /**
     * Click on the special dropdown list element
     *
     * @param dropdownList     dropdown list as a webelement
     * @param elementToSelect  name of the element in the xPath
     * @param dropdownMenuName name of the current dropdown menu
     */
    protected void selectDropdownElement(WebElement dropdownList, String elementToSelect, String dropdownMenuName) {
        click(dropdownList, dropdownMenuName);
        click(getElementFromDropdown(elementToSelect, dropdownMenuName), "Element from dropdown menu " + dropdownMenuName);
    }

    //</editor-folder>

    /**
     * Lose a focus from the web-element
     */
    public void loseFocus() {
        webdriverHelper.executeJavaScript("if (document.activeElement != document.body) document.activeElement.blur();");
    }

    /**
     * Switch to another browser window
     *
     * @param btn Button that opens the new window
     */
    protected String switchWindow(WebElement btn) {
        log.info("Switching to the next window");

        log.debug("Store the current window handle");
        String winHandleBefore = BaseSelenium.getSeleniumDriver().getWindowHandle();
        log.debug("The first window for window handler has been remembered");

        log.debug("Perform the click operation that opens new window");
        click(btn, "Button on the new opened window");

        log.debug("Switch to the new opened window");
        for (String winHandle : BaseSelenium.getSeleniumDriver().getWindowHandles()) {
            BaseSelenium.getSeleniumDriver().switchTo().window(winHandle);
        }

        //log.debug("Performing actions on the new window");
        return winHandleBefore;
    }

    /**
     * Close this window and switch back to the first browser window
     *
     * @param winHandleBefore The first browser window
     */
    protected void switchWindowBack(String winHandleBefore) {
        /*log.debug("Close the new window, if that window no more required");
        BaseSelenium.getSeleniumDriver().close();*/

        log.debug("Switching back to original browser (first window)");
        BaseSelenium.getSeleniumDriver().switchTo().window(winHandleBefore);

        log.info("Continue with original browser (first window)");
    }

    /**
     * Pressing Enter key and mentioning it in log
     *
     * @param webElement  webelemt (e.g. input field) that need to push 'Enter' button
     * @param webElemName where WebDriver have to push 'Enter' key
     */
    public void pressEnter(WebElement webElement, String webElemName) {
        log.info("Pushing 'ENTER' button on the " + webElemName);
        webdriverHelper.pressKey(webElement, Keys.RETURN);
    }

    /**
     * Sending keys into the DropDown menu on the web-page
     * The point is we have ability to send keys into this input-style dropdown menu and push the 'Enter' key after that
     *
     * @param webElement Current dropdown menu (input-style)
     * @param inputText  What we have to insert
     */
    protected void sendDropDownMenuInput(WebElement webElement, String inputText, String inputName) {
        log.info("Sending keys into the " + inputName + " Dropdown Menu on the " + getPAGE_NAME() + ".\tKeys: " + inputText);
        sendKeys(webElement, inputText, "'Company' input field");
        pressEnter(webElement, "'Company' input field");
    }
}