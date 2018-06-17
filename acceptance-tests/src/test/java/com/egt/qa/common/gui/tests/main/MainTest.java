package com.egt.qa.common.gui.tests.main;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.pages.main.MainPageObject;
import com.egt.qa.common.gui.pages.services.BeginAppointmentToDoctor;
import com.egt.qa.common.gui.pages.services.FinishAppointmentToDoctor;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainTest extends AbstractTest {
    @BeforeClass
    public static void login() {
        loggingIn();
    }

    @Test(description = "Запись пользователя ко врачу", priority = 1)
    @TestCaseID("105")
    public void makeAppointmentToDoctor() throws InterruptedException {
        MainPageObject mainPage = new MainPageObject();
        BeginAppointmentToDoctor beginAppointmentToDoctor = new BeginAppointmentToDoctor();
        FinishAppointmentToDoctor finishAppointmentToDoctor = new FinishAppointmentToDoctor();

        mainPage.open();
        mainPage.isOpen();
        Thread.sleep(5000);
        mainPage.pressMakeAppointmentButton();
        Thread.sleep(5000);
        beginAppointmentToDoctor.isOpen();
        beginAppointmentToDoctor.writeRequest();
        beginAppointmentToDoctor.pressMakeRequestButton();
        finishAppointmentToDoctor.writeRequest();
    }

    @Test(description = "Услуги для иностранных граждан на английском языке", priority = 2)
    @TestCaseID("106")
    public void englishVersion() {
        MainPageObject mainPage = new MainPageObject();
        String languageTo = "en";
        mainPage.open();
        mainPage.setLanguage(languageTo);
        mainPage.checkLocation("Saratov");
    }

    @AfterClass
    public void testCleanUp() {
        MainPageObject mainPage = new MainPageObject();
        mainPage.setLanguage("ru");
    }
}