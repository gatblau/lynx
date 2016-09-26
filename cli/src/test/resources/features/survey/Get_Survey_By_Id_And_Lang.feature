Feature: Get Survey Data by Id and Language

Scenario: Get Survey
  Given the survey design is known
  Given there is a survey instance for the survey design
  Given the survey instance identifier is known
  Given the survey language is known
  When a request for the survey data is made
  Then the survey data is retrieved
