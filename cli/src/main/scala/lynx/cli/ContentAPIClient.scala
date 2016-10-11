package lynx.cli

import java.lang.Object
import javax.annotation.{PostConstruct, PreDestroy}
import javax.inject.Named
import javax.ws.rs.client.{Client, ClientBuilder, Entity}
import javax.ws.rs.core.{GenericType, MediaType}

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

  def postOne[T](payload: T, path: String) : ApiResult =
    client
      .target(path)
      .request()
      .post(entity(payload), classOf[ApiResult])

  def postMany[T](payload: T, path: String) : Array[ApiResult] =
    client
      .target(path)
      .request()
      .post(entity(payload), classOf[Array[ApiResult]])

  def getById[T](path: String, id: Int, lang: String) : T =
    client
      .target(path+"/{id}")
      .resolveTemplate("id", id.toString)
      .queryParam("lang", lang)
      .request(MediaType.APPLICATION_JSON)
      .get(new GenericType[T](){})

  @PostConstruct
  private def init() : Unit = { client = ClientBuilder.newClient() }

  @PreDestroy
  private def dispose() : Unit = { client.close() }

  override def getHost(): String = uri

  override def createGroup(group: Group): ApiResult = postOne(group, URI_GROUP(uri))

  override def registerUser(registrationDetailsList: Array[RegistrationRequest]): Array[ApiResult] = postMany(registrationDetailsList, URI_REGISTRATION(uri))
  override def activateAccount(activationDetailsList: Array[ActivationRequest]): Array[ApiResult] = postMany(activationDetailsList, URI_ACTIVATION(uri))
  override def changePassword(request: PwdChangeRequest): ApiResult = postOne(request, URI_CHANGE_PWD(uri))

  // content functions
  override def createContents(request: Array[ContentCreate]) = postMany(request, URI_CONTENT(uri))
  override def createContent(request: ContentCreate) = createContents(Array[ContentCreate](request))(0)
  override def getContent(id: Int, lang: String): ContentData = getById(URI_CONTENT(uri), id, lang)

}
