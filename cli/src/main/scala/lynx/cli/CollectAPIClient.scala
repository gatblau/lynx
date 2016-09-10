package lynx.cli

import javax.annotation.{PostConstruct, PreDestroy}
import javax.enterprise.inject.{New, Produces}
import javax.inject.{Inject, Named}

import lynx.api.{CollectApi, Group, Registration, Result}
import javax.ws.rs.client.{Client, ClientBuilder, Entity}
import javax.ws.rs.core.MediaType;

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
  private def post[T](payload: T, path: String) : Result = client.target(path).request().post(entity(payload), classOf[Result])

  @PostConstruct
  private def init() : Unit = { client = ClientBuilder.newClient() }

  @PreDestroy
  private def dispose() : Unit = { client.close() }

  override def createGroup(group: Group): Result = post(group, s"$uri/api/group")
  override def registerRecipients(registrationDetailsList: List[Registration]): Result = post(registrationDetailsList, s"$uri/api/register")

}
