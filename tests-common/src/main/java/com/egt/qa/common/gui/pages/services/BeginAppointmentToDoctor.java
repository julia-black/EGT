package com.egt.qa.common.gui.pages.services;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeginAppointmentToDoctor extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//h1[@class='ng-binding']")
    private WebElement lblTitle;
    @FindBy(xpath = ".//span[contains(., 'Запись  на')]")
    private WebElement btnAppointmentToDoctor;
    @FindBy(xpath = ".//*[contains(@class, 'epgu-checkbox')]/*[contains(@class, 'epgu-checkbox')]")
    private WebElement chckbxAttachmentToHospital;
    @FindBy(xpath = ".//*[@label='Электронная запись']")
    private WebElement radiobtnElectronicResuest;
    @FindBy(xpath = ".//*[@class='btn-base medium btn-frgu btn-handcraft ng-binding']")
    private WebElement btnMakeRequest;

    //</editor-folder>

    private final String PAGE_NAME = "'Запись на прием к врачу'";

    @Override
    protected String getPAGE_URL() {
        return "10066?from=main";
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
        isOpen("Запись на прием к врачу", lblTitle);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void writeRequest() {
        click(chckbxAttachmentToHospital, "галочка 'Прикрепление к поликлинике'");
        click(radiobtnElectronicResuest, "переключатель 'Электронная запись'");
    }

    public void pressMakeRequestButton() {
        click(btnMakeRequest, "кнопка 'Записаться'");
    }
}
