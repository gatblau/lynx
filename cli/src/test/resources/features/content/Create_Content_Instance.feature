Feature: Create a Content Instance using a Content Definition
  As an Administrator
  I want to create a new content instance using a specific content definition
  So that one or more users can be added to the content instance
  So they can access and update data for that instance

  Scenario: Create a Content Instance
    Given a content definition exists
    Given the content instance details are known
    When a request to create a content instance is made
    Then the content instance is created
