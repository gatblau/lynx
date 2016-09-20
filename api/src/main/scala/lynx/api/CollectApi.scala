package lynx.api

import java.util.ArrayList

trait CollectApi {

  def getHost(): String

  // on-boarding functions
  def registerRespondent(registrationDetailsList: ArrayList[RegistrationRequest]): ArrayList[ApiResult]
  def activateAccount(activationDetailsList: ArrayList[ActivationRequest]) : ArrayList[ApiResult]
  def changePassword(request: PwdChangeRequest): ApiResult

  def createGroup(group: Group) : ApiResult
}

object CollectApi {
  def URI_GROUP(host: String) = s"$host/api/group"
  def URI_REGISTER(host: String) = s"$host/api/register"
  def URI_ACTIVATE(host: String) = s"$host/api/activate"
  def URI_CHANGE_PWD(host: String) = s"$host/api/password"
}