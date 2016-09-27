package features.login

import javax.inject.{Inject, Named, Singleton}

import cucumber.api.PendingException
import cucumber.api.java.en.And
import lynx.api.{ApiResult, CollectApi, Group}
import util._

@Singleton
class LoginSteps {

  @Inject
  @Named("testCollectAPIClient")
  var client: CollectApi = _

  @Inject
  var db: DatabaseDriver = _

  @And("^the respondent is registered$")
  def the_respondent_is_registered() : Unit = {
    db.setup("/data/active_respondent.xml")
  }

  @And("^the respondent credentials are known$")
  def the_respondent_credentials_are_known() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^a request to login is made$")
  def a_request_to_login_is_made() : Unit = {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  @And("^an access token is retrieved$")
  def an_access_token_is_retrieved() : Unit = {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException()
  }
}