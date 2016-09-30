Feature: Get Content Data by Id and Language

Scenario: Get Content Data by Identifier and Language
  Given the content definition is known
  Given there is a content instance for the content definition
  Given the content instance identifier is known
  Given the content language is known
  When a request for the content instance data is made
  Then the content data is retrieved
