package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.cucumber.steps.CommonSteps;
import org.fundacionjala.pivotal.pageobjects.task.Task;
import org.testng.Assert;

import java.util.Map;

/**
 * steps for task feature.
 */
public class TasksSteps {
    private Task task;

    /**
     * setup for task class.
     */
    public TasksSteps(final Task task) {
        this.task = task;
    }

    /**
     * click enable.
     */
    @When("^I click the enable button of tasks$")
    public void iClickTheEnableButtonOfTasks() {
        task.clickEnableTaskButton();
    }

    /**
     * set title.
     * @param value map.
     */
    @When("^I set the name of the task$")
    public void iSetTheNameOfTheTask(final Map<String, String> value) {
        task.setAddEditText(value.get("task"));
    }

    @When("^I set the name of the new task$")
    public void iSetTheNameOfTheNewTask(final Map<String, String> value) {
        task.setAddNewTaskText(value.get("task"));
    }

    /**
     * save task.
     */
    @When("^I save the task$")
    public void iSaveTheTask() {
        task.clickSaveNewTaskButton();
    }

    /**
     * verify creation.
     */
    @Then("^I verify if the task is \"([^\"]*)\"$")
    public void iVerifyIfTheTaskWasCreated(final String expectedTask) {
        String[] keys = expectedTask.split("\\.");
        StringBuilder taskString = new StringBuilder();
        if (keys.length >= 2) {
            taskString.append(CommonSteps.getTablesMap().get(keys[0]).get(keys[1]));
        }
        Assert.assertEquals(task.getFirstTask(), taskString.toString());
    }

    /**
     * get the first task created.
     */
    @When("^I select the task$")
    public void iSelectTheTask() {
        task.clickFirstTask();
    }

    /**
     * save the edit task.
     */
    @When("^I save the edited task$")
    public void iSaveTheEditedTask() {
        task.clickSaveEditTaskButton();
    }
}
