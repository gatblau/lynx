Feature: Get Surveys by Respondent

Scenario: Get Surveys
  Given a respondent is registered
  Given two or more surveys have been associated with the respondent
  When a request is made for surveys associated with the respondent
  Then a list of two or more surveys is retrieved