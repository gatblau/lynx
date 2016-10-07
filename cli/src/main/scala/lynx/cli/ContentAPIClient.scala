package lynx.cli

import javax.annotation.{PostConstruct, PreDestroy}
import javax.inject.Named
import javax.ws.rs.client.{Client, ClientBuilder, Entity}
import javax.ws.rs.core.MediaType

import lynx.api._
import lynx.api.ContentAPI._

@Named
class ContentAPIClient()
  extends ContentAPI {

  var client : Client = _
  var username : String = _
  var password : String = _
  var uri : String = _

  def setup(target: String, user: String, pwd: String) : Unit = {
    username = user
    password = pwd
    uri = target
  }

  private def entity[T](obj: T): Entity[T] = Entity.entity(obj, MediaType.APPLICATION_JSON)
  def postOne[T](payload: T, path: String) : ApiResult = client.target(path).request().post(entity(payload), classOf[ApiResult])
  def postMany[T](payload: T, path: String) : Array[ApiResult] = client.target(path).request().post(entity(payload), classOf[Array[ApiResult]])

  @PostConstruct
  private def init() : Unit = { client = ClientBuilder.newClient() }

  @PreDestroy
  private def dispose() : Unit = { client.close() }

  override def getHost(): String = uri
  override def createGroup(group: Group): ApiResult = postOne(group, URI_GROUP(uri))
  override def registerRespondent(registrationDetailsList: Array[RegistrationRequest]): Array[ApiResult] = postMany(registrationDetailsList, URI_REGISTER(uri))
  override def activateAccount(activationDetailsList: Array[ActivationRequest]): Array[ApiResult] = postMany(activationDetailsList, URI_ACTIVATE(uri))
  override def changePassword(request: PwdChangeRequest): ApiResult = postOne(request, URI_CHANGE_PWD(uri))

  // content functions
  override def createContents(request: Array[Content]) = postMany(request, URI_CREATE_CONTENT(uri))
  override def createContent(request: Content) = createContents(Array[Content](request))(0)
}
