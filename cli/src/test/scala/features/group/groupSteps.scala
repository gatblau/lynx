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

  @Inject
  var driver : DatabaseDriver = _

  @Inject
  var db : DatabaseDriver = _

  @And("^group information is known$")
  def group_information_is_known() : Unit = {
    Cache.set(Keys.GROUP, new Group("Business Unit"))
  }

  @And("^the group does not exist in the data source")
  def the_group_does_not_exist_in_the_data_source() : Unit = {
    driver.setup("/data/empty.xml")
  }

  @And("^a request to create the group is made$")
  def a_request_to_create_the_group_is_made() : Unit = {
    val result = client.createGroup(Cache.get(Keys.GROUP))
    Cache.set(Keys.RESULT, result)
  }

  @And("^group is created$")
  def group_is_created() : Unit = {
    val result : Result = Cache.get(Keys.RESULT)
    assert(result.success, "Response success flag returned false! " + result.message)
    assert(
      db.query("select * from `group` where id = %s", result.id.toString()).getRowCount == 1,
      "Group entry was not created in the database!"
    )
  }
}
