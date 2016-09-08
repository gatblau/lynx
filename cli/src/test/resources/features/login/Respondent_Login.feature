Feature: Respondent Login

  Scenario: Login
    Given the respondent is registered
    Given the respondent credentials are known
    When a request to login is made
    Then an access token is retrieved