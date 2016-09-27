package features.group

import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.{Group, ApiResult}
import util.Testing
import util.Keys._

@Singleton
class GroupSteps extends Testing {

  @And("^group information is known$")
  def group_information_is_known() : Unit = {
    set(GROUP, new Group("Business Unit"))
  }

  @And("^the group does not exist in the data source")
  def the_group_does_not_exist_in_the_data_source() : Unit = {
    db.setup("/data/empty.xml")
  }

  @And("^a request to create the group is made$")
  def a_request_to_create_the_group_is_made() : Unit = {
    set(RESULT, client.createGroup(get(GROUP)))
  }

  @And("^group is created$")
  def group_is_created() : Unit = {
    val result : ApiResult = get(RESULT)
    assert(result.success, "Response success flag returned false! " + result.message)
    assert(
      db.query("select * from `group` where id = %s", result.id.toString()).getRowCount == 1,
      "Group entry was not created in the database!"
    )
  }
}
