package com.egt.qa.common.gui.tests;

import com.egt.qa.common.gui.ScreenListener;
import com.egt.qa.common.gui.pages.main.MainPageObject;
import com.egt.qa.common.gui.pages.main.SignInPageObject;
import com.egt.qa.common.gui.webdriver.BaseSelenium;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({ScreenListener.class})
public class AbstractTest {
    private static final String LOGIN_MOBILE = "+7 (900) 123-12-12";
    private static final String LOGIN_SNILS = "123-123-123 12";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "username";

    /**
     * Initialization of the WebDriver
     */
    //@BeforeTest TODO For Parallel execution
    @BeforeSuite
    @Parameters("browser")
    public static void initSelenium(String browser) throws Exception {
        BaseSelenium.setup(browser);
    }

    protected static void loggingIn() {
        MainPageObject mainPage = new MainPageObject();
        mainPage.open();
        mainPage.isOpen();
        mainPage.signingIn(LOGIN_MOBILE, PASSWORD, "Телефон");
        mainPage.isLogin(NICKNAME);
    }

    protected static void loggingIn(String loginType, boolean firstVisit) {
        MainPageObject mainPage = new MainPageObject();
        SignInPageObject signInPage = new SignInPageObject();
        String login = LOGIN_MOBILE;
        if (loginType.equals("СНИЛС")) {
            login = LOGIN_SNILS;
        }
        mainPage.open();
        mainPage.isOpen();
        if (!firstVisit)
        {
            mainPage.signingInForNotFirstVisit(login, PASSWORD);
        } else {
            mainPage.signingIn(login, PASSWORD, loginType);
        }
        mainPage.isLogin(NICKNAME);
    }

    @AfterSuite
    public static void closeSelenium() {
        BaseSelenium.close();
    }

    /*@BeforeClass
    public static void login() {
        MainPageObject mainPage = new MainPageObject();
        loggingIn();
        *//*if (!mainPage.isLoginWithoutAssert()) {
            loggingIn();
        }*//*
    }

    @AfterClass
    public static void logout() throws InterruptedException {
        MainPageObject mainPage = new MainPageObject();
        if (mainPage.isLoginWithoutAssert()) {
            mainPage.logOut();
        }
        Thread.sleep(10000);
        mainPage.open();
        mainPage.isOpen();
        mainPage.IsLoggedOut();
    }*/
}