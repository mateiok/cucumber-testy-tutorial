package org.fasttrackit.automation;

import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PreferencesTest extends TestBase {

    private PreferencesView page = new PreferencesView();
//private PreferencesPage page;
//public PreferencesTest(){
  //  page = PageFactory.initElements(driver, PreferencesPage.class);
//}

    @Test
    public void preferencesWindowShouldCloseTest(){
        doLogin(USER_NAME, PASSWORD);
        page.open();
        page.close();

    }
        @Test
        public void tryToChangePassWithInvalidPreviewPasswordTest() {
            changePassword("wrong.pass", "new.pass", "new.pass");

            WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
            String message = statusMsg.getText();
            assertThat(message, is("Your preview password is incorrect!"));

        }

    @Test
    public void tryToChangePassWithInvalidConfirmPassTest(){
        changePassword("eu.pass", "new.pass", "new.pass.wrong");

        WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
        String message = statusMsg.getText();
        assertThat(message, is("Password does not match the confirm password!"));
    }

    @Test
    public void successChangePassTest(){
        changePassword(PASSWORD, "new.pass", "new.pass");

        WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
        String message = statusMsg.getText();
        assertThat(message, is("Your password has been successfully changed."));

       page.close();
        WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
        logoutBtn.click();

        PASSWORD = "new.pass";
        doLogin(USER_NAME, PASSWORD);
        logoutBtn = driver.findElement(By.linkText("Logout"));
        logoutBtn.click();

        //revert to old pass
        changePassword("new.pass", "eu.pass", "eu.pass");
        statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
        message = statusMsg.getText();
        assertThat(message, is("Your password has been successfully changed."));
        PASSWORD = "eu.pass";
    }


    private void changePassword(String pass, String newPass, String repeatPass) {
        doLogin(USER_NAME, PASSWORD);
        Utils.sleep(400);
        page.open();
        Utils.sleep(400);
        page.changePassword(pass, newPass, repeatPass);

    }

}


