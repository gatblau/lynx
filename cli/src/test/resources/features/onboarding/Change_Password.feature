Feature: Change Password

  Scenario: Change Password
    Given a user is active
    Given the user password change details are known
    Given a password change email template to notify the user is defined
    When a request is sent to change the password
    Then the change password result is successful
    Then an activation code has been created
    Then the user's login has been disabled
    Then the activation code has been emailed to the user