package com.egt.qa.common.gui.pages.main;

import com.egt.qa.common.gui.pages.AbstractPageObject;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MainPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//*[@id='content']//h3[contains(@class,'popular-title')]")
    private WebElement lblTitlePopular;
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
    @FindBy(xpath = ".//span[contains(., 'Запись  на')]")
    private WebElement btnAppointmentToDoctor;
    @FindBy(xpath = ".//*[contains(@class, 'civi-picker__title')]")
    private WebElement btnSelectLanguage;
    @FindBy(xpath = ".//*[contains(@class, 'lang-picker-lang-rus')]")
    private WebElement btnSelectLanguageRUS;
    @FindBy(xpath = ".//*[contains(@class, 'lang-picker-lang-gbr')]")
    private WebElement btnSelectLanguageGBR;
    @FindBy(xpath = ".//*[@class='location-button__label-text']")
    private WebElement lblLocation;

    //</editor-folder>

    private final String PAGE_NAME = "'Главная страница'";

    @Override
    protected String getPAGE_URL() {
        return "";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Main page is opened.
     */
    public void isOpen() {
        log.info("Is page opened " + getPAGE_NAME());
        isOpen("Популярное на портале", lblTitlePopular);
        //isOpen("Войти", btnLogin);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
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

    public void pressMakeAppointmentButton() {
        click(btnAppointmentToDoctor, "кнопка 'Записаться на прием к врачу'");
    }

    public void setLanguage(String language) {
        click(btnSelectLanguage, "кнопка выбора языка");
        if (language.equals("GBR"))
        {
            click(btnSelectLanguageGBR, "кнопка 'Великобритания'");
        }
    }

    public void checkLocation(String location) {
        Assert.assertEquals(lblLocation.getText(), location);
    }
}
