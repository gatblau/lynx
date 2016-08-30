import javax.enterprise.inject.Produces

import lynx.api._
import lynx.cli.CollectAPIClient

class CollectApiTestProducer {
  @Produces
  def getCollectApi(): CollectApi = {
    new CollectAPIClient("test", "test", "http://localhost:9000")
  }
}
