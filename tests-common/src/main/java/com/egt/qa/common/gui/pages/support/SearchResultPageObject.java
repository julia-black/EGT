package com.egt.qa.common.gui.pages.support;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchResultPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//h1[@class='h1']")
    private WebElement lblTitle;
    @FindBy(xpath = ".//*[@id='page-wrap']//div[@class='epgu-breadcrumbs content-center-box']/ul/li[2]/span")
    private WebElement lblActiveMenu;
    @FindBy(xpath = ".//*[@class='list ng-scope'][1]//*[@class='row-title ng-binding']")
    private WebElement lblFirstResult;

    //</editor-folder>

    @Override
    protected String getPAGE_URL() {
        return "search?query=";
    }
    @Override
    protected String getPAGE_NAME() {
        return "Поиск";
    }

    public void isOpen() {
        log.info("Проверяем, что заголовок появился и равен " + getPAGE_NAME());
        webdriverHelper.isOpen(getPAGE_NAME(), lblActiveMenu);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void checkFirstResult(String searchText) {
        org.testng.Assert.assertEquals(lblFirstResult.getText(), searchText);
    }
}
