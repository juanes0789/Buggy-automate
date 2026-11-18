@login
Feature: Log in to and log out of Buggy Cars Rating
  As a registered user of Buggy Cars Rating
  I want to log in and log out
  So that I can vote and comment securely

  @smoke @happy-path
  Scenario: Successful login with valid credentials
    Given there is a registered user named "Ana"
    And the user is on the Buggy Cars Rating home page
    When the user logs in with their credentials
    Then the navigation bar shows the greeting "Hi, Ana"
    And the logout option is shown

  @exception-path
  Scenario: Login fails with a wrong password
    Given there is a registered user named "Ana"
    And the user is on the Buggy Cars Rating home page
    When the user logs in with their username and the password "WrongPassword1!"
    Then the system shows the authentication error "Invalid username/password"

  @exception-path
  Scenario: Login fails with a user that does not exist
    Given the user is on the Buggy Cars Rating home page
    When the user enters the username "userThatDoesNotExist2026" and the password "wrongPassword"
    Then the system shows the authentication error "Invalid username/password"

  @alternate-path
  Scenario: Log out
    Given there is a registered user named "Ana"
    And the user is on the Buggy Cars Rating home page
    And the user logs in with their credentials
    When the user logs out
    Then the login form is shown again
