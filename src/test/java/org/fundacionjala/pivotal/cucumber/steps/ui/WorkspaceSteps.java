package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.workspaces.NewWorkspaceForm;
import org.fundacionjala.pivotal.pages.workspaces.Workspaces;
import org.testng.Assert;

import java.util.Map;

/**
 * Workspace steps.
 */
public class WorkspaceSteps {
    private NewWorkspaceForm pageForm;
    private Workspaces workspace;
    private String workspaceName;
    private Dashboard dashboard;

    /**
     * Dependency injection.
     * @param dashboard to begin test.
     */
    public WorkspaceSteps(final Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * Enter environment for workspace.
     */
    @When("^I switch tab to workspaces$")
    public void iSwitchTabToWorkspaces() {
        dashboard.clickWorkspaceTab();
    }

    /**
     * Click the create button to enter the form.
     */
    @When("^I click the create button$")
    public void iClickTheCreateButton() {
        pageForm = dashboard.clickCreateWorkspaceButton();
    }
    /**
     * fill workspace form.
     * @param value name
     */
    @And("^I create a new workspace with fields:$")
    public void iCreateANewWorkspaceWithFields(final Map<String, String> value) {
        workspaceName = value.get("name") + System.currentTimeMillis();
        pageForm.setWorkspaceNameTextField(workspaceName);
        workspace = pageForm.clickCreateSubmit();
    }

    /**
     * Check if the workspace is created.
     */
    @Then("^I verify if the workspace is created$")
    public void iVerifyIfTheWorkspaceIsCreated() {
        Assert.assertTrue(workspaceName.equalsIgnoreCase(workspace.getWorkspaceName()));
    }
}
