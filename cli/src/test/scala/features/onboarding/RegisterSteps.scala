package features.onboarding

import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.{ApiResult, Registration}
import util.Testing
import util.Keys._
import java.util.ArrayList

@Singleton
class RegisterSteps extends Testing {

  @And("^a list of details of respondents to be registered is known$")
  def a_list_of_details_of_respondents_to_be_registered_is_known() : Unit = {
    val regs = new ArrayList[Registration]()
    regs.add(Registration("John", "Wayne", "john.wayne@mail.com", 1))
    regs.add(Registration("Joanne", "Mills", "jo.mills@mail.com", 1))
    set(REGISTRATION, regs)
  }

  @And("^a request to register the respondents in the list is made$")
  def a_request_to_register_the_respondents_in_the_list_is_made() : Unit = {
    try {
      set(RESULT, client.registerRecipients(get(REGISTRATION)))
      set(ERROR, null)
    }
    catch {
      case ex : Exception => {
        val e = ex
        set(ERROR, ex.getMessage())
      }
    }
  }

  @And("^the respondents are registered$")
  def the_respondents_are_registered() : Unit = {
    val error : String = get(ERROR)
    assert(get(ERROR) == null, "Request Failed " + error)
  }

  @And("^an activation code for each of the respondents has been created$")
  def an_activation_code_for_each_of_the_respondents_has_been_created() : Unit = {
    val respondent = db.query("select * from respondent")
    assert(respondent.getRowCount() == 2, "Respondents not created in the database")
    val code1 : String = respondent.getValue(0, "activation_code").asInstanceOf[String]
    val code2 : String = respondent.getValue(1, "activation_code").asInstanceOf[String]
    assert(code1 != null && code1.length() > 0, "Activation code not created")
    assert(code1 != null && code1.length() > 0, "Activation code not created")
  }

  @And("^the respondents have been notified by email$")
  def the_respondents_have_been_notified_by_email() : Unit = {

  }
}
