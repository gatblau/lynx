Feature: Get Survey Data by Id and Language

Scenario: Get Survey
  Given a survey definition is defined
  Given the survey definition identifier is known
  Given the survey language is known
  When a request for the survey data is made
  Then the survey data is retrieved
