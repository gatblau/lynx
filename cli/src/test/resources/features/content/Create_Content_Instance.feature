Feature: Create a Content Instance using a Content Definition
  As an Administrator
  I want to create a new content instance using a specific content definition
  So that one or more users can be added to the content instance
  So they can access and update data for that instance

  Scenario: Create a Content Instance
    Given a content definition exists
    Given the user creating the content is registered
    Given the user is an administrator for the content definition
    Given the request is valid
    When a request to create a content instance is made
    Then the content instance is created

  Scenario: Gives an error if the user is not a Content Administrator
    Given a content definition exists
    Given the user creating the content is registered
    Given the user is not an administrator for the content definition
    Given the request is valid
    When a request to create a content instance is made
    Then the content instance is not created
    Then an error indicating the user is not allowed is received

  Scenario: Gives an error if the user is not registered
    Given a content definition exists
    Given the user creating the content is not registered
    Given the request is valid
    When a request to create a content instance is made
    Then the content instance is not created
    Then an error indicating the user is not registered is received

  Scenario: Gives an error if the content definition does not exists
    Given a content definition does not exists
    Given the user creating the content is registered
    Given the request is valid
    When a request to create a content instance is made
    Then the content instance is not created
    Then an error indicating the content definition does not exist is received

  Scenario: Gives an error if the request is invalid
    Given a content definition exists
    Given the user creating the content is registered
    Given the user is an administrator for the content definition
    Given the request is invalid
    When a request to create a content instance is made
    Then the content instance is not created
    Then an error indicating the request is invalid is received

  Scenario: Gives an error if the request has missing values
    Given a content definition exists
    Given the user creating the content is registered
    Given the user is an administrator for the content definition
    Given the request has missing values
    When a request to create a content instance is made
    Then the content instance is not created
    Then an error indicating the request is invalid is received