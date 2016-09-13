Feature: Register Survey Respondents

  Scenario: Register Respondents
    Given a list of details of respondents to be registered is known
    Given the respondents are not already registered
    Given an email template to notify the respondents is defined
    When a request to register the respondents in the list is made
    Then the respondents are registered
    Then an activation code for each of the respondents has been created
    Then the respondents have been notified by email