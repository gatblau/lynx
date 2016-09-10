package features.onboarding

import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.{Registration, Result}
import util.Testing
import util.Keys._

@Singleton
class RegisterSteps extends Testing {

  @And("^a list of details of recipients to be registered is known$")
  def a_list_of_details_of_recipients_to_be_registered_is_known() : Unit = {
    set(REGISTRATION, List(
      new Registration("John", "Wayne", "john.wayne@mail.com", 1),
      new Registration("Joanne", "Mills", "jo.mills@mail.com", 1)
    ))
  }

  @And("^a request to register the recipients in the list is made$")
  def a_request_to_register_the_recipients_in_the_list_is_made() : Unit = {
    set(RESULT, client.registerRecipients(get(REGISTRATION)))
  }

  @And("^the recipients are registered$")
  def the_recipients_are_registered() : Unit = {
    val result : Result = get(RESULT)
    assert(result.success, "Response success flag returned false! " + result.message)
  }

  @And("^a registration code for each of the recipients has been created$")
  def a_registration_code_for_each_of_the_recipients_has_been_created() : Unit = {

  }

  @And("^the recipients have been notified by email$")
  def the_recipients_have_been_notified_by_email() : Unit = {

  }
}
