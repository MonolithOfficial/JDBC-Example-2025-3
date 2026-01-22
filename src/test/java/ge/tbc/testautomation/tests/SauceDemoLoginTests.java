package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.DatabaseSteps;
import ge.tbc.testautomation.data.UserDataProvider;
import ge.tbc.testautomation.data.UserModel;
import ge.tbc.testautomation.steps.LoginSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SauceDemoLoginTests {
    DatabaseSteps databaseSteps;
    LoginSteps loginSteps;

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        databaseSteps = new DatabaseSteps();
        page = browserContext.newPage();
        loginSteps = new LoginSteps(page);
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "getAllUsers")
    public void login(UserModel user) {
        page.navigate("https://saucedemo.com");
        loginSteps
                .fillUsername(user.getUsername())
                .fillPassword(user.getPassword())
                .clickLoginButton();
    }
}
