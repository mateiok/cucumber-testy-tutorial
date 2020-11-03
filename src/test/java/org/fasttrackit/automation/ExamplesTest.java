package org.fasttrackit.automation;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class ExamplesTest /*extends TestBase */{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamplesTest.class);

    WebLocator firstName = new WebLocator().setText("John");
    WebLocator lastName = new WebLocator().setText("Moore");

    WebLocator email = new WebLocator().setText("nickwhite@mail.com");
    WebLocator row = new WebLocator().setTag("tr").setChildNodes(email);
    CheckBox select = new CheckBox(row);

    @Test
    public void selectRowTest() {
       // doLogin(USER_NAME, PASSWORD);

        Utils.sleep(400);
        select.click();
        System.out.println(select.getSelector());

        row.setChildNodes(firstName, lastName);
        Utils.sleep(400);
        select.click();
        LOGGER.debug(select.getSelector().toString());

    }

    public static void main(String[] args) {
        ExamplesTest test = new ExamplesTest();
        System.out.println(test.select.getSelector());

       test.row.setChildNodes(test.firstName, test.lastName);
        Utils.sleep(400);
        LOGGER.debug(test.select.getSelector().toString());

    }
}
