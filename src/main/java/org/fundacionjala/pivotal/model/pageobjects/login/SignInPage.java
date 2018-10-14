package org.fundacionjala.pivotal.model.pageobjects.login;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacionjala.pivotal.model.pageobjects.AbstractPage;
import org.fundacionjala.pivotal.model.pageobjects.WebDriverManager;
import org.fundacionjala.pivotal.model.pageobjects.dashboard.PageDashboard;
import org.fundacionjala.pivotal.util.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Pivotal Sign In Page.
 * @author Angelica Lopez, Kevin Sanchez - AWT-[01].
 * @version 0.1
 */
public class SignInPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger("Sign in pivotal");
    //Using FindBy for locating elements
    @FindBy(css = "input[type='text'][id='credentials_username']")
    private WebElement usernameTextBox;
    @FindBy(css = "input[type='submit'][value='NEXT']")
    private WebElement nextButton;
    //Using FindBy for locating elements
    @FindBy(css = "input[type='password'][id='credentials_password']")
    private WebElement passwordTextBox;
    @FindBy(css = "input[type='submit'][value='SIGN IN']")
    private WebElement signInButton;

    /**
     * This method is to set Email in the email text box.
     * @param userEmail .
     */
    public void setUsernameTextBox(final String userEmail) {
        CommonActions.waitAndSetText(usernameTextBox, userEmail);
    }

    /**
     * This method is to click on Login Button.
     */
    public void clickOnLoginButton() {
        CommonActions.waitAndClick(nextButton);
    }

    /**
     * This method is to set Email in the email text box.
     * @param userPassword .
     */
    public void setPasswordTextBox(final String userPassword) {
        CommonActions.waitAndSetText(passwordTextBox, userPassword);
    }

    /**
     * This method is to click on Login Button.
     * @return .
     */
    public PageDashboard clickSignInButton() {
        CommonActions.waitAndClick(signInButton);
        return new PageDashboard();
    }

    /**
     * Missing to implement switchUser.
     * @param username .
     * @param password .
     * @return .
     */
    public static PageDashboard loginAs(final String username, final String password) {
        WebDriverManager.getInstance().getDriver().get(HomePage.HOME_PAGE_URL);
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.setUsernameTextBox(username);
        LOGGER.log(Level.INFO, "Sign in on pivotaltracker.com, user: " + username);
        signInPage.clickOnLoginButton();
        signInPage.setPasswordTextBox(password);
        return signInPage.clickSignInButton();
    }

}
