package com.egt.qa.common.gui.pages.services;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinishAppointmentToDoctor extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//*[@class='PGU-FormTitleText ng-binding ng-scope']")
    private WebElement lblTitle;
    @FindBy(xpath = "//*[@id=\"7201\"]/div/ul/li/a/i/i")
    private WebElement btnWhoNeedsHelp;

    //</editor-folder>

    private final String PAGE_NAME = "'Запись на прием к врачу'";

    @Override
    protected String getPAGE_URL() {
        return "10066/1/form?from=main";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Main page is opened.
     */
    public void isOpen() {
        log.info("Проверяется открытие страницы " + getPAGE_NAME());
        isOpen("Запись к врачу", lblTitle);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void writeRequest() {
        click(btnWhoNeedsHelp, "галочка 'Мне'");
    }
}
