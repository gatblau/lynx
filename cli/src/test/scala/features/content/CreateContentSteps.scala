package features.content

import javax.inject.Singleton

import cucumber.api.PendingException
import cucumber.api.java.en.And
import lynx.api.{Content, Descriptor, English, Spanish}
import util.Testing
import util.Keys._

@Singleton
class CreateContentSteps extends Testing {

  @And("^a content definition exists$")
  def a_content_definition_exists() : Unit = {
    db.setup("/data/empty.xml")
    db.setup("/data/content_definition.xml")
  }

  @And("^the user creating the content is registered$")
  def the_user_creating_the_content_is_registered() : Unit = {
    db.setup("/data/active_user.xml")
  }

  @And("^the user is an administrator for the content definition$")
  def the_user_is_an_administrator_for_the_content_definition() : Unit = {
    db.setup("/data/user_is_content_admin.xml")
  }

  @And("^a request to create a content instance is made$")
  def a_request_to_create_a_content_instance_is_made() : Unit = {
    try {
      set(ERROR, null)
      set(RESULT, client.createContent(Content(1, Array(
        Descriptor("Test Survey", "Collects test survey data", English.code),
        Descriptor("Encuesta de prueba", "Collecta datos para la encuesta de prueba.", Spanish.code)
      ))))
    }
    catch {
      case ex : Exception => set(ERROR, ex.getMessage())
    }
  }

  @And("^the content instance is created$")
  def the_content_instance_is_created() : Unit = {
    checkForError("Content instance could not be created.")
  }

  @And("^the user is not an administrator for the content definition$")
  def the_user_is_not_an_administrator_for_the_content_definition() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^the content instance is not created$")
  def the_content_instance_is_not_created() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^the user creating the content is not registered$")
  def the_user_creating_the_content_is_not_registered() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^an error indicating the user is not registered is received$")
  def an_error_indicating_the_user_is_not_registered_is_received() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^a content definition does not exists$")
  def a_content_definition_does_not_exists() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^an error indicating the content definition does not exist is received$")
  def an_error_indicating_the_content_definition_does_not_exist_is_received() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^the request is invalid$")
  def the_request_is_invalid() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^an request to create a content instance is made$")
  def an_request_to_create_a_content_instance_is_made() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^an error indicating the request is invalid is received$")
  def an_error_indicating_the_request_is_invalid_is_received() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^the request has missing values$")
  def the_request_has_missing_values() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
