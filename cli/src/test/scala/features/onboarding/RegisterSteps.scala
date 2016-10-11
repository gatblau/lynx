package features.onboarding

import lynx.api.ContentAPI._
import javax.inject.Singleton
import javax.ws.rs.client.{Client, Entity}
import javax.ws.rs.core.MediaType

import cucumber.api.java.en.And
import lynx.api.{ApiResult, RegistrationRequest}
import util.Keys._
import util.Testing

import scala.beans.BeanProperty

case class IncorrectPayload (
    @BeanProperty asgd: String,
    @BeanProperty ghyyn: String
  )

@Singleton
class RegisterSteps extends Testing {

  @And("^a registration email template to notify the users is defined$")
  def a_registration_email_template_to_notify_the_users_is_defined() : Unit = {
    db.setup("/data/registration_email_template.xml")
  }

  @And("^a list of details of users to be registered is known$")
  def a_list_of_details_of_users_to_be_registered_is_known() : Unit = {
    set(REGISTRATION_DATA, regPayloadOK())
  }

  @And("^the users are not already registered$")
  def the_users_are_not_already_registered() : Unit = {
    db.setup("/data/no_users.xml")
  }

  @And("^a request to register the users is made$")
  def a_request_to_register_the_users_is_made() : Unit = {
    try {
      set(ERROR, null)
      if (get(REGISTRATION_DATA).isInstanceOf[Array[RegistrationRequest]]) {
        set(RESULT, client.registerUser(get(REGISTRATION_DATA)))
      }
      else {
        set(RESULT, client.postMany(get(REGISTRATION_DATA), URI_REGISTRATION("http://localhost:9000")))
      }
    }
    catch {
      case ex : Exception => {
        val msg = ex.getMessage()
        set(ERROR, msg)
      }
    }
  }

  @And("^the users are registered$")
  def the_users_are_registered() : Unit = {
    val error : String = get(ERROR)
    assert(get(ERROR) == null, "Request Failed " + error)
  }

  @And("^an activation code for each of the users has been created$")
  def an_activation_code_for_each_of_the_users_has_been_created() : Unit = {
    val user = db.query("select * from user")
    assert(user.getRowCount() == 2, "Respondents not created in the database")
    val code1 : String = user.getValue(0, "activation_code").asInstanceOf[String]
    val code2 : String = user.getValue(1, "activation_code").asInstanceOf[String]
    assert(code1 != null && code1.length() > 0, "Activation code not created")
    assert(code1 != null && code1.length() > 0, "Activation code not created")
  }

  @And("^the users have been notified by email$")
  def the_users_have_been_notified_by_email() : Unit = {
    // for info only at this stage
  }

  @And("^an incorrect payload is created$")
  def an_incorrect_payload_is_created() : Unit = {
    val payload = IncorrectPayload("A", "B")
    set(REGISTRATION_DATA, Array(payload))
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

  @And("^the users are already registered$")
  def the_users_are_already_registered() : Unit = {
    db.setup("/data/users_to_be_reg.xml")
  }

  @And("^an error indicating the users are already registered is received$")
  def an_error_indicating_the_users_are_already_registered_is_received() : Unit = {
    val results : Array[ApiResult] = get(RESULT)
    for (i <- 0 to results.length - 1) {
      assert(!results(i).success, "Response was successful but it should not have.")
      assert(results(i).message.contains("already registered"), "Error message is wrong.")
    }
  }

  @And("^an email template to notify the users is not defined$")
  def an_email_template_to_notify_the_users_is_not_defined() : Unit = {
    db.setup("/data/no_email_template.xml")
  }

  @And("^an error indicating the email template is not defined is received$")
  def an_error_indicating_the_email_template_is_not_defined_is_received() : Unit = {
    val error : String = get(ERROR)
    assert(error == null || error.length() == 0, "An exception ocurred.")
    val results : Array[ApiResult] = get(RESULT)
    for (i <- 0 to results.length - 1) {
      assert(!results(i).success.asInstanceOf[Boolean], "Response was successful but it should not have.")
      assert(results(i).message.contains("Email Template"), "Error message is wrong.")
    }
  }

  private def regPayloadOK()  = {
    val r = db.load("/data/users_to_be_reg.xml").getTable("user")
    val regs = new Array[RegistrationRequest](r.getRowCount())
    for (i <- 0 to r.getRowCount() - 1) {
      regs(i) = RegistrationRequest(
        r.getValue(i, "firstname").toString(),
        r.getValue(i, "lastname").toString(),
        r.getValue(i, "email").toString(), 1)
    }
    regs
  }

  private def regPayloadMissingValues()  = {
    val regs = new Array[RegistrationRequest](2)
    for (i <- 0 to 1) regs(i) = RegistrationRequest("", "", "", 1)
    regs
  }

  private def regPayloadWrongEmailTemplate()  = {
    val r = db.load("/data/users_to_be_reg.xml").getTable("user")
    val regs = new Array[RegistrationRequest](r.getRowCount())
    for (i <- 0 to r.getRowCount()) {
      regs(i) = RegistrationRequest(
        r.getValue(i, "firstname").toString(),
        r.getValue(i, "lastname").toString(),
        r.getValue(i, "email").toString(), 2000)
    }
    regs
  }
}
