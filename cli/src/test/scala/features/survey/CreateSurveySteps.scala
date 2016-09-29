package features.survey

import javax.inject.Singleton

import cucumber.api.PendingException
import cucumber.api.java.en.And
import util.Testing

@Singleton
class CreateSurveySteps extends Testing {

  @And("^a survey definition exists$")
  def a_survey_definition_exists() : Unit = {
    db.setup("/data/survey_definition.xml")
  }

  @And("^the survey details are known$")
  def the_survey_details_are_known() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^a request to create a survey is made$")
  def a_request_to_create_a_survey_is_made() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^the survey is created$")
  def the_survey_is_created() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }
}
