package com.egt.qa.common.gui.pages.main;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import com.egt.qa.common.gui.webdriver.BaseSelenium;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SignInPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//h1[@class='base-title b-page-title']")
    private WebElement lblTitleEnter;
    @FindBy(xpath = ".//*[@class='button-base button-long button-base--enter']")
    private WebElement btnLogin;
    @FindBy(xpath = ".//*[@class='another-user']")
    private WebElement lblAnotherUser;
    @FindBy(xpath = ".//*[@class='this-user']")
    private WebElement lblThisUser;
    @FindBy(xpath = ".//*[@data-bind='click: toMobileOrEmail']")
    private WebElement tabMobile;
    @FindBy(xpath = ".//*[@data-bind='click: toSnils']")
    private WebElement tabSnils;
    @FindBy(xpath = ".//input[@id='mobileOrEmail']")
    private WebElement inputMobile;
    @FindBy(xpath = ".//input[@id='snils']")
    private WebElement inputSnils;
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//*[@data-bind='click: loginByPwd']")
    private WebElement btnEnter;

    //</editor-folder>

    @Override
    protected String getPAGE_URL() {
        return "https://esia.gosuslugi.ru/idp/login/pwd/switch?cc=bp";
    }
    @Override
    protected String getPAGE_NAME() {
        return "'Авторизация'";
    }

    /**
     * Check that Sign In page is opened.
     */
    public void isOpen() {
        log.info("Is page opened " + getPAGE_NAME());
        isOpen("Популярное на портале\nдля Саратовской области", lblTitleEnter);
        //isOpen("Войти", btnLogin);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.openDirectLink(getPAGE_URL());
    }

    public void signingIn(String login, String password, String loginType) {
        click(btnLogin, "кнопка 'Войти'");
        click(lblAnotherUser, "текст 'Другой пользователь'");
        if (loginType.equals("СНИЛС"))
        {
            click(tabSnils, "вкладка 'СНИЛС'");
            click(inputSnils, "поле ввода 'СНИЛС'");
            sendKeys(inputSnils, login, "'СНИЛС'");
        } else {
            click(tabMobile, "вкладка 'Телефон или почта'");
            sendKeys(inputMobile, login, "'Мобильный телефон'");
        }
        sendKeys(inputPassword, password, "'Пароль'");
        click(btnEnter, "кнопка 'Войти'");
    }

    public void signingInForNotFirstVisit(String login, String password) {
        click(btnLogin, "кнопка 'Войти'");
        Assert.assertEquals(lblThisUser.getText(), login);
        sendKeys(inputPassword, password, "'Пароль'");
        click(btnEnter, "кнопка 'Войти'");
    }

    public void IsLoggedOut() {
        Assert.assertFalse(isLoginWithoutAssert());
    }
}
