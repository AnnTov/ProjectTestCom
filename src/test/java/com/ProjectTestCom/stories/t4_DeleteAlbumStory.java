package com.ProjectTestCom.stories;

import com.ProjectTestCom.steps.AddAlbumSteps;
import com.ProjectTestCom.steps.HeaderSteps;
import com.ProjectTestCom.steps.LoginSteps;
import com.ProjectTestCom.utils.PropertyLoader;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by olsa on 5/11/2016.
 */
@RunWith(SerenityRunner.class)
public class t4_DeleteAlbumStory {
    private String browserFilePath = "src/test/resources/browser.properties";
    private String BrowserProfile = PropertyLoader.getProperty(browserFilePath, "BrowserProfile");

    private String propertyFilePath = "src/test/resources/login.properties";
    //private String propertyFilePath = "src/test/resources/login_live.properties";
    private String email = PropertyLoader.getProperty(propertyFilePath, "email");
    private String password = PropertyLoader.getProperty(propertyFilePath, "password");

    @Managed
    WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    LoginSteps loginSteps;

    @Steps
    AddAlbumSteps addAlbumSteps;

    @Steps
    HeaderSteps headerSteps;

    @Before
    public void setup() throws IOException {
        String dir = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", dir + "\\src\\drivers\\geckodriver.exe");

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(1600, TimeUnit.SECONDS);

        loginSteps.openLoginPage();
        loginSteps.PageComplete(driver);
        loginSteps.pressLoginLink();

        loginSteps.enterLogin(email);
        loginSteps.enterPassword(password);
        loginSteps.clickEnter(driver);
        loginSteps.PageComplete(driver);

        headerSteps.openMyMnassaPage(driver);
        headerSteps.openMyMedia(driver);
        loginSteps.PageComplete(driver);
    }

    @After
    public void tearDown() {driver.quit();
    }
    @Test
    public void viewAlbum_Settings_DeleteAlbum() {
        addAlbumSteps.openViewAlbumPage();
        addAlbumSteps.openSettingsMenu();
        addAlbumSteps.selectDeleteMenu();
        addAlbumSteps.clickDeleteButton();
        addAlbumSteps.checkAlbumDeleted(driver);
    }
    @Test
    public void viewAlbum_Settings_CancelDeleteAlbum() {
        addAlbumSteps.openViewAlbumPage();
        addAlbumSteps.openSettingsMenu();
        addAlbumSteps.selectDeleteMenu();
    }
}
