package lynx.cli

import java.util.ArrayList
import javax.annotation.{PostConstruct, PreDestroy}
import javax.inject.Named
import javax.ws.rs.client.{Client, ClientBuilder, Entity}
import javax.ws.rs.core.MediaType

import lynx.api.{ApiResult, CollectApi, Group, Registration}

@Named
class CollectAPIClient()
  extends CollectApi {

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
  private def postOne[T](payload: T, path: String) : ApiResult = client.target(path).request().post(entity(payload), classOf[ApiResult])
  private def postMany[T](payload: T, path: String) : ArrayList[ApiResult] = client.target(path).request().post(entity(payload), classOf[ArrayList[ApiResult]])

  @PostConstruct
  private def init() : Unit = { client = ClientBuilder.newClient() }

  @PreDestroy
  private def dispose() : Unit = { client.close() }

  override def createGroup(group: Group): ApiResult = postOne(group, s"$uri/api/group")
  override def registerRecipients(registrationDetailsList: ArrayList[Registration]): ArrayList[ApiResult] = postMany(registrationDetailsList, s"$uri/api/register")

}
