package lynx.api

import java.util.ArrayList

trait CollectApi {
  def getHost(): String

  def registerRecipients(registrationDetailsList: ArrayList[Registration]): ArrayList[ApiResult]
  def createGroup(group: Group) : ApiResult

  def postOne[T](payload: T, path: String) : ApiResult
  def postMany[T](payload: T, path: String) : ArrayList[ApiResult]
}

object CollectApi {
  def URI_GROUP(host: String) = s"$host/api/group"
  def URI_REGISTER(host: String) = s"$host/api/register"
}