package org.fundacionjala.pivotal.pages.workspaces;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.CommonActions;
import org.fundacionjala.core.ui.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for Pivotal Workspaces Page.
 */
public class Workspaces extends AbstractPage {

    @FindBy(id = "workspace_name")
    private WebElement workspaceNameTextField;

    @FindBy(css = "a[data-aid='navTab-more']")
    private WebElement moreButton;

    @FindBy(id = "delete_link")
    private WebElement deleteLink;

    @FindBy(id = "confirm_delete")
    private WebElement deleteConfirmButton;

    /**
     * Getter of project name input field on create project settings page.
     * @return webElement object.
     */
    public String getWorkspaceName() {
        enterWorkspaceSettings();
        return workspaceNameTextField.getAttribute("value");
    }

    /**
     * Click on more button inside project page.
     */
    public void enterWorkspaceSettings() {
        WebDriverManager.getInstance().getWait().until(ExpectedConditions.elementToBeClickable(moreButton));
        String newURL = WebDriverManager.getInstance().getDriver().getCurrentUrl().replace("/n", "") + "/settings";
        WebDriverManager.getInstance().getDriver().navigate().to(newURL);
    }

    /**
     * Delete the current project page.
     */
    public void deleteWorkspace() {
        CommonActions.waitElement(deleteLink);
        CommonActions.scrollToElement(deleteLink);
        CommonActions.click(deleteLink);
        CommonActions.click(deleteConfirmButton);
    }
}
