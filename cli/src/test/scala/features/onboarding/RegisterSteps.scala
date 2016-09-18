package features.onboarding

import java.util.ArrayList
import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.Registration
import util.Keys._
import util.Testing

import scala.beans.BeanProperty

case class IncorrectPayload (
    @BeanProperty asgd: String,
    @BeanProperty ghyyn: String
  )

@Singleton
class RegisterSteps extends Testing {

  @And("^an email template to notify the respondents is defined$")
  def an_email_template_to_notify_the_respondents_is_defined() : Unit = {
    db.setup("/data/email_template.xml")
  }

  @And("^a list of details of respondents to be registered is known$")
  def a_list_of_details_of_respondents_to_be_registered_is_known() : Unit = {
    set(REGISTRATION_DATA, regPayloadOK())
  }

  @And("^the respondents are not already registered$")
  def the_respondents_are_not_already_registered() : Unit = {
    db.setup("/data/no_respondents.xml")
  }

  @And("^a request to register the respondents is made$")
  def a_request_to_register_the_respondents_is_made() : Unit = {
    try {
      set(ERROR, null)
      set(RESULT, client.registerRespondent(get(REGISTRATION_DATA)))
    }
    catch {
      case ex : Exception => set(ERROR, ex.getMessage())
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
    // for info only at this stage
  }

  @And("^an incorrect payload is created$")
  def an_incorrect_payload_is_created() : Unit = {
    val payload = IncorrectPayload("A", "B")
    set(REGISTRATION_DATA, new ArrayList(){payload})
  }

  @And("^a bad request response is received$")
  def a_bad_request_response_is_received() : Unit = {
    val error : String = get(ERROR)
    assert(get(ERROR) != null, "The request succeeded but should have failed.")
    assert(get(ERROR).toString().contains("HTTP 400"), "A BAD REQUEST response was not sent by the server.")
  }

  @And("^a payload with missing information is created$")
  def a_payload_with_missing_information_is_created() : Unit = {
    set(REGISTRATION_DATA, regPayloadMissingValues())
  }

  @And("^the respondents are already registered$")
  def the_respondents_are_already_registered() : Unit = {
    db.setup("/data/respondents_to_be_reg.xml")
  }

  @And("^an error indicating the respondents are already registered is received$")
  def an_error_indicating_the_respondents_are_already_registered_is_received() : Unit = {
    val results : ArrayList[java.util.LinkedHashMap[String, String]] = get(RESULT)
    for (i <- 0 to results.size() - 1) {
      assert(!results.get(i).get("success").asInstanceOf[Boolean], "Response was successful but it should not have.")
      assert(results.get(i).get("message").contains("already registered"), "Error message is wrong.")
    }
  }

  @And("^an email template to notify the respondents is not defined$")
  def an_email_template_to_notify_the_respondents_is_not_defined() : Unit = {
    db.setup("/data/no_email_template.xml")
  }

  @And("^an error indicating the email template is not defined is received$")
  def an_error_indicating_the_email_template_is_not_defined_is_received() : Unit = {
    val error : String = get(ERROR)
    assert(error == null || error.length() == 0, "An exception ocurred.")
    val results : ArrayList[java.util.LinkedHashMap[String, String]] = get(RESULT)
    for (i <- 0 to results.size() - 1) {
      assert(!results.get(i).get("success").asInstanceOf[Boolean], "Response was successful but it should not have.")
      assert(results.get(i).get("message").contains("Email Template"), "Error message is wrong.")
    }
  }

  private def regPayloadOK()  = {
    val r = db.load("/data/respondents_to_be_reg.xml").getTable("respondent")
    val regs = new ArrayList[Registration]()
    for (i <- 0 to r.getRowCount() - 1) {
      regs.add(Registration(
        r.getValue(i, "firstname").toString(),
        r.getValue(i, "lastname").toString(),
        r.getValue(i, "email").toString(), 1))
    }
    regs
  }

  private def regPayloadMissingValues()  = {
    val regs = new ArrayList[Registration]()
    for (i <- 0 to 1) {
      regs.add(Registration(
        "",
        "",
        "", 1))
    }
    regs
  }

  private def regPayloadWrongEmailTemplate()  = {
    val r = db.load("/data/respondents_to_be_reg.xml").getTable("respondent")
    val regs = new ArrayList[Registration]()
    for (i <- 0 to 1) {
      regs.add(Registration(
        r.getValue(i, "firstname").toString(),
        r.getValue(i, "lastname").toString(),
        r.getValue(i, "email").toString(), 2000))
    }
    regs
  }
}
