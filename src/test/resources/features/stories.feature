Feature: Manage Stories
  Scenario: Create a new story
    Given username "user1" and password "password1"
    Then I click the add story button
    And I fill the story title
    |title|testStory|
    Then I verify if the story was created