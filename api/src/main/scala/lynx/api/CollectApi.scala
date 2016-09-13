package lynx.api

import java.util.ArrayList

trait CollectApi {
  def registerRecipients(registrationDetailsList: ArrayList[Registration]): ArrayList[ApiResult]
  def createGroup(group: Group) : ApiResult
}
