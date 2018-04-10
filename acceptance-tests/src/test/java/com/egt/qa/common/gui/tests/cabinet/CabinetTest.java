package com.egt.qa.common.gui.tests.cabinet;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CabinetTest extends AbstractTest {
    @Test(description = "Выход из личного кабинета")
    @TestCaseID("201")
    public void checkLogout() {
        Assert.assertFalse(false);
    }
}