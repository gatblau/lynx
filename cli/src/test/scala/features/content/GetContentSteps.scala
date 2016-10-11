package features.content

import javax.inject.Singleton

import cucumber.api.PendingException
import cucumber.api.java.en.And

import lynx.api._
import util.Testing
import util.Keys._

@Singleton
class GetContentSteps extends Testing {

  private val contentDefId = 1

  @And("^there is a content instance for the content definition$")
  def there_is_a_content_instance_for_the_content_definition() : Unit = {
    val result = client.createContent(ContentCreate(contentDefId, Array(
      Descriptor("Test Survey", "Collects test survey data", English.code),
      Descriptor("Encuesta de prueba", "Collecta datos para la encuesta de prueba.", Spanish.code)
    )))
    set(CONTENT_ID, result.id)
  }

  @And("^a request for the content instance data is made$")
  def a_request_for_the_content_instance_data_is_made() : Unit = {
    client.getContent(2, "en")
  }

  @And("^the content data is retrieved$")
  def the_content_data_is_retrieved() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }
}
