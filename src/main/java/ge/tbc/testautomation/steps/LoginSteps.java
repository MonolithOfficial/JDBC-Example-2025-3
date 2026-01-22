package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {
    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    public LoginSteps fillUsername(String username){
        loginPage.usernameInput.fill(username);

        return this;
    }

    public LoginSteps fillPassword(String password){
        loginPage.passwordInput.fill(password);

        return this;
    }

    public LoginSteps clickLoginButton(){
        loginPage.loginButton.click();

        return this;
    }
}
