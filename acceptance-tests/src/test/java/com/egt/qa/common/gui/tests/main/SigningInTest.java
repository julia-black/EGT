package com.egt.qa.common.gui.tests.main;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SigningInTest extends AbstractTest {
    @BeforeMethod
    public void preparation() {
        logout();
    }

    @Test(description = "Авторизация по СНИЛС", priority=1)
    @TestCaseID("102")
    public void checkLoginSNILS() {
        loggingIn("СНИЛС", true);
    }

    @Test(description = "Авторизация с запомненным мобильным номером")
    @TestCaseID("103")
    public void checkLoginMobileSaved() {
        loggingIn("Мобильный", true);
        logout();
        loggingIn("Мобильный", false);
    }

    @Test(description = "Авторизация с запомненным СНИЛС")
    @TestCaseID("104")
    public void checkLoginSNILSSaved() {
        loggingIn("СНИЛС", true);
        logout();
        loggingIn("СНИЛС", false);
    }
}
