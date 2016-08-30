Feature: Create Group

Scenario: Create Group
  Given group information is known
  When a request to create the group is made
  Then group is created
