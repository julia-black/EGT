package com.egt.qa.common.gui.tests.main;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.pages.main.MainPageObject;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SigningInTest extends AbstractTest {
    @BeforeClass
    public static void login() {
        MainPageObject mainPage = new MainPageObject();
        loggingIn();
    }

    @BeforeMethod
    public void preparation() throws InterruptedException {
        logout();
    }

    @Test(description = "Авторизация по СНИЛС", priority=1)
    @TestCaseID("102")
    public void checkLoginSNILS() {
        loggingIn("СНИЛС", true);
    }

    @Test(description = "Авторизация с запомненным мобильным номером", priority=2)
    @TestCaseID("103")
    public void checkLoginMobileSaved() throws InterruptedException {
        loggingIn("Мобильный", true);
        logout();
        loggingIn("Мобильный", false);
    }

    @Test(description = "Авторизация с запомненным СНИЛС", priority=3)
    @TestCaseID("104")
    public void checkLoginSNILSSaved() throws InterruptedException {
        loggingIn("СНИЛС", true);
        logout();
        loggingIn("СНИЛС", false);
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
    }
}
