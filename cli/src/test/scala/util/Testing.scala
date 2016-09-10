package util

import javax.inject.{Inject, Named}

import lynx.api.CollectApi

import scala.collection.mutable

trait Testing {
  private[Testing] val values = new mutable.WeakHashMap[String, Any]()

  @Inject @Named("testCollectAPIClient")
  var client : CollectApi = _

  @Inject
  var db : DatabaseDriver = _

  def get[T](key: String): T = values.get(key).get.asInstanceOf[T]
  def set(key: String, value: Any) = values.put(key, value)

}
