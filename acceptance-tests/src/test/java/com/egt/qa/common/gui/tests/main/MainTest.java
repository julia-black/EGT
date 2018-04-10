package com.egt.qa.common.gui.tests.main;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.pages.main.MainPageObject;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainTest extends AbstractTest {
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