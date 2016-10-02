package features.onboarding

import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.{ApiResult, PwdChangeRequest}
import util.Keys._
import util.Testing

@Singleton
class ChangeEmailSteps extends Testing {
  val activeRespondentData = "/data/active_user.xml"
  val emailTemplateFile = "/data/pwd_change_email_template.xml"

  @And("^a user is active$")
  def a_user_is_active() : Unit = {
    db.setup(activeRespondentData)
  }

  @And("^a password change email template to notify the user is defined$")
  def a_password_change_email_template_to_notify_the_user_is_defined() : Unit = {
    db.setup(emailTemplateFile)
  }

  @And("^the user password change details are known$")
  def the_user_password_change_details_are_known() : Unit = {
    val email = db.load(activeRespondentData).getTable("user")
    val template = db.load(emailTemplateFile).getTable("email_template")
    val details = PwdChangeRequest(
      email.getValue(0, "email").toString(),
      template.getValue(0, "id").toString().toInt
    )
    set(PWD_CHANGE_DETAILS, details)
  }

  @And("^a request is sent to change the password$")
  def a_request_is_sent_to_change_the_password() : Unit = {
    try {
      set(ERROR, null)
      set(RESULT, client.changePassword(get(PWD_CHANGE_DETAILS)))
    }
    catch {
      case ex : Exception => set(ERROR, ex.getMessage())
    }
  }

  @And("^an activation code has been created$")
  def an_activation_code_has_been_created() : Unit = {
    val changeRequest : PwdChangeRequest = get(PWD_CHANGE_DETAILS)
    val user = db.query("select activation_code from user where email='%s'", changeRequest.email)
    val activationCode = user.getValue(0, "activation_code")
    assert(activationCode != null && activationCode.toString().length() > 0, "Activation code was not created.")
  }

  @And("^the user's login has been disabled$")
  def the_user_s_login_has_been_disabled() : Unit = {
    val changeRequest : PwdChangeRequest = get(PWD_CHANGE_DETAILS)
    val user = db.query("select enabled from user where email='%s'", changeRequest.email)
    val enabled : Boolean = user.getValue(0, "enabled").asInstanceOf[Boolean]
    assert(!enabled, "Login was not disabled.")
  }

  @And("^the activation code has been emailed to the user$")
  def the_activation_code_has_been_emailed_to_the_user() : Unit = {
    // for info only at this stage
  }

  @And("^the change password result is successful$")
  def the_change_password_result_is_successful() : Unit = {
    val result : ApiResult = get(RESULT)
    val changeRequest : PwdChangeRequest = get(PWD_CHANGE_DETAILS)
    assert(result.success, "The result is not successful.")
    assert(result.id == changeRequest.email, "The id in the result does not match the request user email.")
  }
}
