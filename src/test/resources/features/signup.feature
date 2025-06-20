Feature: Signup Functionality

  Scenario: Successful Account Signup
    Given I open the Magento signup page
    When I enter valid signup details
    And I submit the form
    Then I should see a success message "Thank you for registering with Main Website Store."

  Scenario: Signup with missing required fields
    Given I open the Magento signup page
    When I enter valid first name, last name, password and confirm password
    And I leave the email field blank
    And I submit the form
    Then I should see the message "This is a required field."

  Scenario: Signup with weak password
    Given I open the Magento signup page
    When I enter valid first name, last name and email
    And I enter password "123" and confirm password "123"
    And I submit the form
    Then I should see the message "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."

  Scenario: Signup with mismatched password and confirm password
    Given I open the Magento signup page
    When I enter valid first name, last name and email
    And I enter password "Test@12345" and confirm password "Wrong@12345"
    And I submit the form
    Then I should see the message "Please enter the same value again."

  Scenario: Signup with invalid email address format
    Given I open the Magento signup page
    When I enter valid first name, last name, password and confirm password
    And I enter an invalid email "testuser123"
    And I submit the form
    Then I should see the message "Please enter a valid email address (Ex: johndoe@domain.com)."

  Scenario: Sign in with an existing valid account
    Given I open the Magento login page
    When I enter email "exist123@gmail.com" and password "bigbat234@#$T"
    And I click the Sign In button
    Then I should see the account dashboard with "My Account"

