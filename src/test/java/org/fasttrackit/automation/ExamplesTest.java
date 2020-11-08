package org.fasttrackit.automation;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.table.Cell;
import com.sdl.selenium.web.table.Row;
import com.sdl.selenium.web.table.Table;
import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class ExamplesTest extends TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamplesTest.class);

    WebLocator firstName = new WebLocator().setText("John");
    WebLocator lastName = new WebLocator().setText("Moore");

    WebLocator email = new WebLocator().setText("nickwhite@mail.com");
    WebLocator row = new WebLocator().setTag("tr").setChildNodes(email);
    CheckBox select = new CheckBox(row);

    @Test
    public void selectRowTest() {
       doLogin(USER_NAME, PASSWORD);

        Utils.sleep(400);
        select.click();
        System.out.println(select.getSelector());

        row.setChildNodes(firstName, lastName);
        Utils.sleep(400);
        select.click();
        LOGGER.debug(select.getSelector().toString());
    }

    @Test
    public void selectRowFromTable(){
        doLogin(USER_NAME, PASSWORD);
        Table table = new Table().setClasses("table-striped");
        Row row = table.getRow(2);
        CheckBox select = new CheckBox(row);
        Utils.sleep(400);
        select.click();

        Row row2 = table.getRow("nickwhite@mail.com");
        CheckBox select2 = new CheckBox(row2);
        Utils.sleep(400);
        select2.click();

        Row row3 = table.getRow("bobsmith@", SearchType.STARTS_WITH);
        CheckBox select3 = new CheckBox(row3);
        Utils.sleep(400);
        select3.click();

        Row row4 = table.getRow(new Cell(2, "David"), new Cell(3, "Miller"));
        CheckBox select4 = new CheckBox(row4);
        Utils.sleep(400);
        select4.click();

        Row row5 = table.getRow(new Cell("John"), new Cell("Moore"));
        CheckBox select5 = new CheckBox(row5);
        Utils.sleep(400);
        select5.click();
    }


   /*
      public static void main(String[] args) {
        ExamplesTest test = new ExamplesTest();
        System.out.println(test.select.getSelector());
       test.row.setChildNodes(test.firstName, test.lastName);
        Utils.sleep(400);
        LOGGER.debug(test.select.getSelector().toString());
      }
     */

}
