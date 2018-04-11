package com.egt.qa.common.gui.pages.support;

import com.egt.qa.common.gui.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupportPageObject extends AbstractPageObject {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //<editor-folder desc="WebElements">

    @FindBy(xpath = ".//h1[@class='h1']")
    private WebElement lblTitle;
    @FindBy(xpath = ".//*[@id='page-wrap']//div[@class='epgu-breadcrumbs content-center-box']/ul/li[2]/span")
    private WebElement lblActiveMenu;
    @FindBy(xpath = ".//input[@placeholder='Поиск в частых вопросах']")
    private WebElement inputSearch;
    @FindBy(xpath = ".//*[@class='search-ico-container']/*[@class='search-ico']")
    private WebElement btnSearch;
    @FindBy(xpath = ".//*[@ng-click='searchTrigger()']")
    private WebElement lblSearchHint;

    //</editor-folder>

    @Override
    protected String getPAGE_URL() {
        return "help?serviceRecipient=all";
    }
    @Override
    protected String getPAGE_NAME() {
        return "Помощь и поддержка";
    }
    
    public void isOpen() {
        log.info("Проверяется на открытость страница " + getPAGE_NAME());
        isOpen(getPAGE_NAME(), lblTitle);
        //isOpen(getPAGE_NAME(), lblActiveMenu);
    }

    public void open() {
        log.info("Открывается страница " + getPAGE_NAME());
        webdriverHelper.open(getPAGE_URL());
    }

    public void writeQuestion(String searchText) {
        sendKeys(inputSearch, searchText, "поле 'Поиск'");
    }

    public void searchWithButton() {
        click(btnSearch, "кнопка 'Увеличительное стекло'");
    }

    public void searchWithHint() {
        click(lblSearchHint, "подсказка 'Посмотреть результаты поиска по сайту'");
    }
}
