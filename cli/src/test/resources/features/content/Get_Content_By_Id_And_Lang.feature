Feature: Get Content Data by Id and Language

Scenario: Get Content Data by Identifier and Language
  Given a content definition exists
  Given there is a content instance for the content definition
  When a request for the content instance data is made
  Then the content data is retrieved
