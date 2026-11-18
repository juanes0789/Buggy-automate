@registration
Feature: User registration in Buggy Cars Rating
  As a visitor of Buggy Cars Rating
  I want to create an account
  So that I can vote for and comment on car models

  Background:
    Given the user is on the Buggy Cars Rating home page

  @smoke @happy-path
  Scenario: Successful registration with valid data
    When the user opens the registration form
    And fills in the form with a new user, first name "Juan", last name "Perez" and password "Test1234!"
    And submits the registration form
    Then the system shows the registration message "Registration is successful"

  @exception-path
  Scenario: Registration fails with a username that already exists
    Given there is a registered user named "Ana"
    When the user opens the registration form
    And fills in the form with the username of the existing user
    And submits the registration form
    Then the system shows the registration message "UsernameExistsException: User already exists"

  @exception-path @boundary-values
  Scenario Outline: Registration fails when the password breaks the policy (<rule>)
    When the user opens the registration form
    And fills in the form with a new user, first name "Juan", last name "Perez" and password "<password>"
    And submits the registration form
    Then the system shows the registration message "<message>"

    Examples:
      | rule                    | password | message                                 |
      | fewer than 6 characters | Ab1!x    | minimum field size of 6                 |
      | fewer than 8 characters | Ab1!xy   | Password not long enough                |
      | no uppercase letters    | abcdef1! | Password must have uppercase characters |
      | no symbols              | Abcdef12 | Password must have symbol characters    |

  @alternate-path
  Scenario: Password and confirmation do not match
    When the user opens the registration form
    And fills in the form with first name "Juan", last name "Perez", password "Test1234!" and confirmation "Otra1234!"
    Then the validation alert "Passwords do not match" is shown
    And the register button is disabled

  @alternate-path
  Scenario: Registration is not possible with empty fields
    When the user opens the registration form
    And fills in the form with username "", first name "", last name "" and password ""
    Then the register button is disabled

  @boundary-values
  Scenario Outline: Username length limit (<length> characters)
    When the user opens the registration form
    And fills in the form with a username of <length> characters
    Then the register button is <state>

    Examples:
      | length | state    |
      | 50     | enabled  |
      | 51     | disabled |
