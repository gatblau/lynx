package lynx.cli

import javax.annotation.{PostConstruct, PreDestroy}
import javax.enterprise.inject.{New, Produces}
import javax.inject.{Inject, Named}

import lynx.api.{CollectApi, Group, Result}
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

  @PostConstruct
  private def init() : Unit = {
    client = ClientBuilder.newClient()
  }

  @PreDestroy
  private def dispose() : Unit = {
    client.close()
  }

  override def createGroup(group: Group): Result[Group] = {
    val entity : Entity[Group] = Entity.entity(group, MediaType.APPLICATION_JSON)
    val result = client.target(s"$uri/group")
      .request()
      .post(entity, classOf[Result[Group]])
    result
  }
}
