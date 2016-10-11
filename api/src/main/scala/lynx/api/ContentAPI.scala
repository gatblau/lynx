package lynx.api

trait ContentAPI {

  def getHost(): String
  def postOne[T](payload: T, path: String): ApiResult
  def postMany[T](payload: T, path: String): Array[ApiResult]

  // on-boarding functions
  def registerUser(registrationDetailsList: Array[RegistrationRequest]): Array[ApiResult]
  def activateAccount(activationDetailsList: Array[ActivationRequest]) : Array[ApiResult]
  def changePassword(request: PwdChangeRequest): ApiResult

  // content functions
  def createContents(contentList: Array[ContentCreate]): Array[ApiResult]
  def createContent(contentItem: ContentCreate): ApiResult
  def getContent(contentId: Int, languageCode: String): ContentData

  // other
  def createGroup(group: Group) : ApiResult
}

object ContentAPI {
  def URI_GROUP(host: String) = s"$host/api/group"
  def URI_REGISTRATION(host: String) = s"$host/api/registration"
  def URI_ACTIVATION(host: String) = s"$host/api/activation"
  def URI_CHANGE_PWD(host: String) = s"$host/api/password"
  def URI_CONTENT(host: String) = s"$host/api/content"
}