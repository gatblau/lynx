Feature: Create Group

Scenario: Create Group
  Given group information is known
  Given the group does not exist in the data source
  When a request to create the group is made
  Then group is created
