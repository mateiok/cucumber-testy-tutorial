package org.fasttrackit.util;

import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.Browser;
import org.fasttrackit.automation.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
    public static String USER_NAME = "eu@fast.com";
    public static String PASSWORD = "eu.pass";

    public static WebDriver driver;

    protected LoginPage loginPage;

    public TestBase() {
        System.out.println("TestBase Constructor");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }


    static {
        startSuite();
    }

    private static void startSuite() {
        try {
            String browser = "browser.properties";
            driver = WebDriverConfig.getWebDriver(browser);
            driver = WebDriverConfig.getWebDriver(Browser.CHROME);
        } catch (Exception e) {
            LOGGER.error("Exception when start suite", e);
        }
    }

    public void doLogin(String user, String pass) {
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
        loginPage.login(user, pass);
    }}

