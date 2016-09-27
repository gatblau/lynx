package features.login

import javax.inject.{Inject, Named, Singleton}

import cucumber.api.java.en.And
import lynx.api.{CollectApi, Group, ApiResult}
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

  }

  @And("^a request to login is made$")
  def a_request_to_login_is_made() : Unit = {

  }

  @And("^an access features.token is retrieved$")
  def an_access_token_is_retrieved() : Unit = {

  }
}