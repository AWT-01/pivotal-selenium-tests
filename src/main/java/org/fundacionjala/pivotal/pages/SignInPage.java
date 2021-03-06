package org.fundacionjala.pivotal.pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.CommonActions;
import org.fundacionjala.core.ui.WebDriverManager;
import org.fundacionjala.core.util.Environment;
import org.fundacionjala.core.util.exceptions.EnvironmentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Pivotal Sign In Page.
 *
 * @author Angelica Lopez, Kevin Sanchez - AWT-[01].
 * @version 0.1
 */
public class SignInPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger("Sign in pivotal");

    @FindBy(css = "input[type='text'][id='credentials_username']")
    private WebElement usernameTextBox;

    @FindBy(css = "input[type='submit'][value='NEXT']")
    private WebElement nextButton;

    @FindBy(css = "input[type='password'][id='credentials_password']")
    private WebElement passwordTextBox;

    @FindBy(css = "input[type='submit'][value='SIGN IN']")
    private WebElement signInButton;

    /**
     * This method is to set Email in the email text box.
     *
     * @param userEmail .
     */
    public void setUsernameTextBox(final String userEmail) {
        CommonActions.setText(usernameTextBox, userEmail);
    }

    /**
     * This method is to click on Login Button.
     */
    public void clickOnLoginButton() {
        CommonActions.click(nextButton);
    }

    /**
     * This method is to set Email in the email text box.
     *
     * @param userPassword .
     */
    public void setPasswordTextBox(final String userPassword) {
        CommonActions.setText(passwordTextBox, userPassword);
    }

    /**
     * This method is to click on Login Button.
     *
     * @return .
     */
    public Dashboard clickSignInButton() {
        CommonActions.click(signInButton);
        return new Dashboard();
    }

    /**
     * Smart login checking current session with cookies.
     *
     * @param username that.
     * @param password that.
     * @return a Dashboard object
     */
    public static Dashboard credentials(final String username, final String password) {
        String currentSession = StringUtils.EMPTY;
        if (WebDriverManager.getInstance().getDriver().manage().getCookieNamed("lastuser") != null) {
            currentSession = WebDriverManager.getInstance().getDriver().manage().getCookieNamed("lastuser").getValue();
        }
        LOGGER.log(Level.INFO, "Check if user: " + username + "is logged");
        LOGGER.log(Level.INFO, "User logged is: " + currentSession);
        // change sessions if needed
        if (!username.equals(currentSession)) {
            WebDriverManager.getInstance().getDriver().manage().deleteAllCookies();
            try {
                final String url = Environment.getInstance().getProperties().getProperty("url");
                WebDriverManager.getInstance().getDriver().get(url);
                HomePage homePage = new HomePage();
                SignInPage signInPage = homePage.clickOnSignInButton();
                signInPage.setUsernameTextBox(username);
                LOGGER.log(Level.INFO, "Sign in on pivotaltracker.com, user: " + username);
                signInPage.clickOnLoginButton();
                signInPage.setPasswordTextBox(password);
                return signInPage.clickSignInButton();
            } catch (EnvironmentException ex) {
                LOGGER.error("There isn't a url in the config.properties file");
                throw new EnvironmentException();
            }
        } else {
            LOGGER.log(Level.INFO, "User: " + username + "is already logged");
            return new Dashboard();
        }
    }
}
