package com.egt.qa.common.gui.pages.services;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class EducationPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//*[@class='b-content__title service-h1 ']")
    private WebElement lblTitle;

    //</editor-folder>

    private final String PAGE_NAME = "'Высшее образование'";

    @Override
    protected String getPAGE_URL() {
        return "situation/obtain_education/higher_education";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Education page is opened.
     */
    public void isOpen() {
        log.info("Is page opened " + getPAGE_NAME());
        isOpen("Высшее образование", lblTitle);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void openEducationLevelDescription(String educationLevel) {
        click(webdriverHelper.getElementFromXpath(".//a[text()='"+ educationLevel +"']"), "Ссылка на описание уровня образования");
    }
}
