import javax.enterprise.inject.{New, Produces}
import javax.inject.Named

import lynx.api._
import lynx.cli.CollectAPIClient

class CollectApiTestProducer {
  @Produces
  @Named("testCollectAPIClient")
  private def testCollectAPIClient(@New api: CollectAPIClient) : CollectApi = {
    api.setup("test","test","http://localhost:9000")
    api
  }
}
