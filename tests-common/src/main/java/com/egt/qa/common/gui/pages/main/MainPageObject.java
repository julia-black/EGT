package com.egt.qa.common.gui.pages.main;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools. allure .annotations.Step;

public class MainPageObject extends AbstractPageObject {
    private final Logger log = LogManager.getLogger(this.getClass());
    @FindBy(xpath = ".//*[@id='content']//h3[contains(@class,'popular-title')]")
    private WebElement lblTitlePopular;

    //<editor-folder desc="WebElements">
    @FindBy(xpath = ".//input[@id='mobileOrEmail']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//*[@class='button-base button-long button-base--enter']")
    private WebElement btnLogin;

    @Override
    protected String getPAGE_URL() {
        return "";
    }

    //</editor-folder>

    private final String PAGE_NAME = "'Главная страница'";

    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Main page is opened.
     */
    public void isOpen() {
        isOpen("Популярное на портале  ", lblTitlePopular);
    }

    // TODO Step repair
    @Step("Открывается страница " + PAGE_NAME)
    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void signingIn(String login, String password) {
        click(btnLogin, "кнопка 'Войти'");
        sendKeys(inputLogin, login, "'Login'");
        sendKeys(inputPassword, password, "'Password'");
    }

    /**
     * Get the element with special text in the Headline News Table
     *
     * @param text What to look for
     * @return Headline News Table element with the specific text
     */
    public WebElement findElementInHeadlineNewsTable(String text) {
        log.info("Find element with text '" + text + "' in the Headline News table on the main page");
        return webdriverHelper.getElementFromXpath(".//*[@class='mainheader fromdivpadding']//*[text()=' " + text + " ']");
    }

    /**
     * Check that this element does't exists in the Headline News Table on the Main page
     *
     * @param value What to look for
     * @return Headline News Table element status (true if exists)
     */
    public boolean isSpecificArticleFromHeadlineNewsTableExists(String value) {
        log.info("Checking if article '" + value + "' exists on the " + getPAGE_NAME());
        return isExists(".//*[@class='mainheader fromdivpadding']//*[text()=' " + value + " ']", value);
    }
}
