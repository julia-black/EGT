package com.egt.qa.common.gui.tests.services;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.pages.services.EducationLevelsPageObject;
import com.egt.qa.common.gui.pages.services.EducationPageObject;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServicesTest extends AbstractTest {
    @DataProvider(name = "forEducation")
    public Object[] forEducation() {
        return new Object[][]{
                {"Бакалавриат", "Это законченное высшее образование. Поступать на программы бакалавриата могут обладатели среднего общего или среднего профессионального образования. Учеба длится 4 года и предполагает общую фундаментальную подготовку. Для поступления в вуз необходимо написать заявление в приёмную комиссию учебного заведения и представить паспорт и аттестат или диплом об окончании школы или техникума, результаты ЕГЭ. По окончании бакалавриата студенты защищают квалификационную выпускную работу. По ее итогам выдается диплом бакалавра с присвоением квалификации («Академический бакалавр», «Прикладной бакалавр»). Документ дает право работать по профессии или продолжить обучение в магистратуре."},
                {"Магистратура", "Позволяет углубить специализацию по выбранному направлению. На программы магистратуры могут поступать бакалавры и специалисты. Обучение длится не менее двух лет и предусматривает подготовку студента к научно-исследовательской деятельности. Выпускники защищают выпускную работу (магистерскую диссертацию), по итогам которой выдается диплом магистра и присваивается квалификация «Магистр». Выпускники магистратуры имеют право на профессиональную деятельность и могут продолжить образование в аспирантуре."}
        };
    }

    @Test(description = "Открытие описания уровней высшего образования", dataProvider = "forEducation")
    @TestCaseID("401")
    public void openEducationLevels(String[] edDescription) {
        EducationPageObject educationPage = new EducationPageObject();
        EducationLevelsPageObject educationLevelsPage = new EducationLevelsPageObject();
        educationPage.open();
        educationPage.isOpen();
        educationPage.openEducationLevelDescription(edDescription[0]);
        educationLevelsPage.open();
        educationLevelsPage.isOpen();
        educationLevelsPage.checkEducationLevelDescription(edDescription[0], edDescription[1]);
    }
}