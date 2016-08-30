package lynx.api

trait CollectApi {
  def createGroup(group: Group) : Result[Group]
}
