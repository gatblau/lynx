package util

import javax.enterprise.inject.{New, Produces}
import javax.inject.Named

import lynx.api._
import lynx.cli.ContentAPIClient

class CollectApiTestProducer {
  @Produces
  @Named("testCollectAPIClient")
  private def testCollectAPIClient(@New api: ContentAPIClient) : ContentAPI = {
    api.setup("http://localhost:9000", "test","test")
    api
  }
}
