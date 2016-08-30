package lynx.cli

import javax.inject.{Inject, Named}

import lynx.api.{ CollectApi, Group, Result }

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Named
class CollectAPIClient(uri: String, username: String, password: String)
  extends CollectApi {

  override def createGroup(group: Group): Result[Group] = {
    new Result[Group](true, 1, None, "")
  }

}
