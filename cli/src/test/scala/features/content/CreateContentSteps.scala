package features.content

import javax.inject.Singleton

import cucumber.api.PendingException
import cucumber.api.java.en.And
import util.Testing

@Singleton
class CreateContentSteps extends Testing {

  @And("^a content definition exists$")
  def a_content_definition_exists() : Unit = {
    db.setup("/data/content_definition.xml")
  }

  @And("^the content instance details are known$")
  def the_content_instance_details_are_known() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^a request to create a content instance is made$")
  def a_request_to_create_a_content_instance_is_made() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^the content instance is created$")
  def the_content_instance_is_created() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }
}
