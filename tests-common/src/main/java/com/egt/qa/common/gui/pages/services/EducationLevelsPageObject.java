package com.egt.qa.common.gui.pages.services;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class EducationLevelsPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//*[@class='b-content__title service-h1 ']")
    private WebElement lblTitle;
    @FindBy(xpath = ".//h3[@id='bakalavriat']/following-sibling::p[1]")
    private WebElement txtBakalavriatDesc;
    @FindBy(xpath = ".//h3[@id='magistratura']/following-sibling::p[1]")
    private WebElement txtMagistraturaDesc;

    //</editor-folder>

    private final String PAGE_NAME = "'Уровни высшего образования'";

    @Override
    protected String getPAGE_URL() {
        return "situation/obtain_education/higher_education/types_of_higher_education";
    }
    @Override
    protected String getPAGE_NAME() {
        return PAGE_NAME;
    }

    /**
     * Check that Education Levels page is opened.
     */
    public void isOpen() {
        log.info("Is page opened " + getPAGE_NAME());
        isOpen("Уровни высшего образования", lblTitle);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void checkEducationLevelDescription(String educationLevelName, String educationLevelDescription) {
        String id = "bakalavriat";
        if (educationLevelName.equals("Магистратура")) {
            id = "magistratura";
        }
        WebElement desc = webdriverHelper.getElementFromXpath(".//h3[@id='"+ id +"']/following-sibling::p[1]");

        Assert.assertEquals(desc.getText(), educationLevelDescription);
    }
}
