Feature: User Login

  Scenario: Login
    Given the user is registered
    Given the user credentials are known
    When a request to login is made
    Then an access token is retrieved