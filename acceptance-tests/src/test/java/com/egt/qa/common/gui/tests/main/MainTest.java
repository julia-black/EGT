package com.egt.qa.common.gui.tests.main;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends AbstractTest {
    @Test(description = "Авторизация по мобильному номеру")
    @TestCaseID("101")
    public void checkLoginMobile() {
        Assert.assertEquals("***REMOVED***", "***REMOVED***");
    }

    @Test(description = "Авторизация по СНИЛС")
    @TestCaseID("102")
    public void checkLoginSNILS() {
        Assert.assertEquals("***REMOVED***", "***REMOVED***");
    }

    @Test(description = "Авторизация с запомненным мобильным номером")
    @TestCaseID("103")
    public void checkLoginMobileSaved() {
        Assert.assertEquals("***REMOVED***", "***REMOVED***");
    }

    @Test(description = "Авторизация с запомненным СНИЛС")
    @TestCaseID("104")
    public void checkLoginSNILSSaved() {
        Assert.assertEquals("***REMOVED***", "***REMOVED***");
    }

    @Test(description = "Запись пользователя ко врачу")
    @TestCaseID("105")
    public void makeAppointmentToDoctor() {
        Assert.assertEquals("***REMOVED***", "***REMOVED***");
    }

    @Test(description = "Услуги для иностранных граждан на английском языке")
    @TestCaseID("106")
    public void englishVersion() {
        Assert.assertTrue(false);
    }
}