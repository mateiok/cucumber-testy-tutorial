package org.fasttrackit.automation;

import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PreferencesTest<utils> extends TestBase {


        @Test
        public void tryToChangePassWithInvalidPreviewPasswordTest() {
            driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");

            driver.findElement(By.name("username")).sendKeys("eu@fast.com");

            WebElement passwordElement = driver.findElement(By.id("password"));
            passwordElement.sendKeys("eu.pass");
            WebElement loginBtn = driver.findElement(By.id("loginButton"));
            loginBtn.click();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement preferencesBtn = driver.findElement(By.cssSelector(".navbar-header button"));
            preferencesBtn.click();


            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"password\"]"));
            WebElement newPasswordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"newPassword\"]"));
            WebElement confirmPasswordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"newPasswordRepeat\"]"));
            WebElement saveBtn = driver.findElement(By.xpath("//*[@id='preferences-win']//button[text()='Save']"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            passwordField.sendKeys("wrong.pass");
            newPasswordField.sendKeys("new.pass");
            confirmPasswordField.sendKeys("new.pass");
            saveBtn.click();

            WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
            String message = statusMsg.getText();
            assertThat(message, is("Your preview password is incorrect!"));

        }

        @Test
        public void tryToChangePassWithInvalidConfirmPass(){
            changePassword("eu.pass", "new.pass", "new.pass.wrong");

           WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
           String message = statusMsg.getText();
           assertThat(message, is("Password does not match the confirm password!"));
        }

    @Test
    public void successChangePassTest(){
        changePassword("eu.pass", "new.pass", "new.pass");

        WebElement statusMsg = driver.findElement(By.xpath("//*[@id='preferences-win']//*[@class='status-msg']"));
        String message = statusMsg.getText();
        assertThat(message, is("Your password has been successfully changed."));
        WebElement closeBtn = driver.findElement(By.cssSelector("#preferences-win .modal-footer button"));
        closeBtn.click();
        WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
        logoutBtn.click();

    }

    private void changePassword(String pass, String newPass, String repeatPass) {
        Utils.sleep(400);
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");

        driver.findElement(By.name("username")).sendKeys("eu@fast.com");

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys("eu.pass");
        WebElement loginBtn = driver.findElement(By.id("loginButton"));
        loginBtn.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement preferencesBtn = driver.findElement(By.cssSelector(".navbar-header button"));
        preferencesBtn.click();

        Utils.sleep(400);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"password\"]"));
        WebElement newPasswordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"newPassword\"]"));
        WebElement confirmPasswordField = driver.findElement(By.xpath("//*[@id=\"preferences-win\"]//input[@name=\"newPasswordRepeat\"]"));
        WebElement saveBtn = driver.findElement(By.xpath("//*[@id='preferences-win']//button[text()='Save']"));

        passwordField.sendKeys(pass);
        newPasswordField.sendKeys(newPass);
        confirmPasswordField.sendKeys(repeatPass);
        saveBtn.click();


    }
}


