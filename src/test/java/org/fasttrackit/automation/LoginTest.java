package org.fasttrackit.automation;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.nio.file.WatchEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends TestBase {
    @Test
    public void validLoginTest(){
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");

        driver.findElement(By.name("username")).sendKeys("eu@fast.com");

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys("eu.pass111111111111222223444332");
        passwordElement.clear();
        passwordElement.sendKeys("eu.pass");
        WebElement loginBtn = driver.findElement(By.id("loginButton"));
        loginBtn.click();

       //WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
       try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // Utils.sleep(400); the same as try and catch

        WebLocator container = new WebLocator().setClasses("controls");
        WebLocator logoutBtn = new WebLocator(container).setText("Logout");
        logoutBtn.click();

    }
    @Test
    public void invalidUserAndPassword (){
      driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
      WebElement usernameElement = driver.findElement(By.name("username"));
      usernameElement.sendKeys("sksk");
      WebElement passwordElement = driver.findElement(By.id("password"));
      passwordElement.sendKeys("kjskj");
      WebElement loginBtn = driver.findElement(By.id("loginButton"));
      loginBtn.click();

      WebElement errorMsg = driver.findElement(By.className("error-msg"));

      String message = errorMsg.getText();
      System.out.println(message);

       assertThat (message, is("Invalid user or password!"));
    }



    @Test
    public void preferencesWindowShouldCloseTest(){
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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement closeBtn = driver.findElement(By.cssSelector("#preferences-win .modal-footer button"));
        closeBtn.click();
    }

}



