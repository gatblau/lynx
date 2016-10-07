package lynx.api

trait ContentAPI {
  def getHost(): String
  def postOne[T](payload: T, path: String): ApiResult
  def postMany[T](payload: T, path: String): Array[ApiResult]

  // on-boarding functions
  def registerRespondent(registrationDetailsList: Array[RegistrationRequest]): Array[ApiResult]
  def activateAccount(activationDetailsList: Array[ActivationRequest]) : Array[ApiResult]
  def changePassword(request: PwdChangeRequest): ApiResult

  // content functions
  def createContents(contentList: Array[Content]): Array[ApiResult]
  def createContent(contentItem: Content): ApiResult

  // other
  def createGroup(group: Group) : ApiResult
}

object ContentAPI {
  def URI_GROUP(host: String) = s"$host/api/group"
  def URI_REGISTER(host: String) = s"$host/api/register"
  def URI_ACTIVATE(host: String) = s"$host/api/activate"
  def URI_CHANGE_PWD(host: String) = s"$host/api/password"
  def URI_CREATE_CONTENT(host: String) = s"$host/api/content"
}