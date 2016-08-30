package features.group

import javax.inject.{Inject, Named, Singleton}

import cucumber.api.java.en.And
import lynx.api.{CollectApi, Group, Result}
import util._

@Singleton
class groupSteps {

  @Inject
  @Named("testCollectAPIClient")
  var client : CollectApi = _

  @And("^group information is known$")
  def group_information_is_known() : Unit = {
    Cache.set(Keys.GROUP, new Group("Dev Manager", "In charge of App development"))
  }

  @And("^a request to create the group is made$")
  def a_request_to_create_the_group_is_made() : Unit = {
    val result = client.createGroup(Cache.get(Keys.GROUP))
    Cache.set(Keys.RESULT, result)
  }

  @And("^group is created$")
  def group_is_created() : Unit = {
    val result : Result[Group] = Cache.get(Keys.RESULT)
    assert(result.success)
    assert(result.id > 0)
  }
}
