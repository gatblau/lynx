Feature: Get Content Instances by User

Scenario: Get Contents
  Given a user is registered
  Given two or more content instances have been associated with the user
  When a request is made for content instances associated with the user
  Then a list of two or more content instances is retrieved