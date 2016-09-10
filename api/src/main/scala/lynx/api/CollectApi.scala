package lynx.api

trait CollectApi {
  def registerRecipients(registrationDetailsList: List[Registration]): Result
  def createGroup(group: Group) : Result
}
