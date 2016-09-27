Feature: Create a Survey using a Survey Definition
  As an Administrator
  I want to create a new survey using a specific survey definition
  So that one or more respondents can be added to the Survey
  So they can access and respond the Survey

  Scenario: Create a Survey
    Given a survey definition exists
    Given the survey details are known
    When a request to create a survey is made
    Then the survey is created
