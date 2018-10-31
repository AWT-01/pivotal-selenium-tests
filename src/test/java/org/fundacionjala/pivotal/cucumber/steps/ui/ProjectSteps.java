package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.cucumber.steps.CommonSteps;
import org.testng.Assert;
import java.util.Map;
import org.fundacionjala.pivotal.pageobjects.dashboard.NewProjectForm;
import org.fundacionjala.pivotal.pageobjects.projects.Projects;

/**
 * Class for project steps.
 */
public class ProjectSteps {

    private NewProjectForm formPage;
    private Projects projects;
    private String projectName;

    /**
     * Constructos for injection dependency.
     * @param formPage the form for project creation
     */
    public ProjectSteps(final NewProjectForm formPage) {
        this.formPage = formPage;
    }

    /**
     * Fields from data table are used to create project.
     *
     * @param values .
     */
    @When("^I create a new project with fields:$")
    public void iCreateANewProjectWithFields(final Map<String, String> values) {
        values.keySet().forEach(form -> formPage.getStrategyFormMap(values).get(form).executeSteps());
        projectName = String.valueOf(System.currentTimeMillis());
        formPage.setProjectNameTextField(projectName);
        projectName = values.get("name") + projectName;
        //submit data to create new project
        projects = formPage.clickCreateButton();
    }

    /**
     * Verify project is created.
     */
    @Then("^I verify if the project is created$")
    public void iVerifyIfTheProjectIsCreated() {
        projects.enterProjectSettings();
        Assert.assertTrue(projectName.equalsIgnoreCase(projects.getProjectNameInputField().getAttribute("value")));
    }

    /**
     * open a story searching data on a map using a key.
     * @param storyNameKey name of key.
     */
    @And("^I open a story \"([^\"]*)\"$")
    public void iOpenAStory(final String storyNameKey) {
        String[] keys = storyNameKey.split("\\.");
        StringBuilder storyName = new StringBuilder();
        if (keys.length >= 2) {
            storyName.append(CommonSteps.getResponsesMap().get(keys[0]).jsonPath().get(keys[1]).toString());
        }
        Projects.openStoryByName(storyName.toString());
    }
}