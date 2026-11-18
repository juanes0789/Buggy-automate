@voting
Feature: Vote for and comment on a car model
  As a registered user of Buggy Cars Rating
  I want to vote for a model and leave a comment
  So that I can share my opinion with other users

  @e2e @smoke @happy-path
  Scenario: A new user signs up, logs in, votes for and comments on a model
    Given the user signs up and logs in with a new account
    When the user goes to a model from the overall ranking
    And writes the comment "Great car for automated testing UdeA 2026"
    And submits the vote
    Then the comment appears in the list of comments of the model
    And the vote count of the model increases
    And the system shows the voting message "Thank you for your vote!"
    And the voting form is not shown

  @exception-path
  Scenario: A visitor who is not logged in cannot vote
    Given the user is on the Buggy Cars Rating home page
    When the user goes to a model from the overall ranking
    Then the system shows the voting message "You need to be logged in to vote."
    And the voting form is not shown
