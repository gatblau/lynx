Feature: Add Respondent to Survey
  As an Administrator
  I want to add a Respondent to a Survey
  So that they are notified by e-mail
  So they can access the Survey
  So they can respond the Survey

  Scenario: Add Respondent to Survey
    Given a Survey has been created
    Given a Respondent is registered and active
    When a request to add the Respondent to the Survey is made
    Then the Respondent is added to the Survey