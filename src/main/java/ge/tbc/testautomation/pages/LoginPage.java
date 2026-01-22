package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    public Locator usernameInput;
    public Locator passwordInput;
    public Locator loginButton;

    public LoginPage(Page page) {
        this.usernameInput = page.locator("#user-name");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("#login-button");
    }
}
