Feature: Add User to Content Instance
  As an Administrator
  I want to add a User to a Content Instance
  So that they are notified by e-mail
  So they can access the content in the instance
  So they can update the content in the instance

  Scenario: Add a User to a Content Instance
    Given a content Instance has been created
    Given a User is registered and active
    When a request to add the User to the content instance is made
    Then the User is added to the content instance