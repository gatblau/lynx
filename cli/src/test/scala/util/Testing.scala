package util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.inject.{Inject, Named}

import lynx.api.CollectApi

import scala.collection.mutable

trait Testing {
  private[Testing] val values = new mutable.WeakHashMap[String, Any]()

  @Inject @Named("testCollectAPIClient")
  var client : CollectApi = _

  @Inject
  var db : DatabaseDriver = _

  def get[T](key: String): T = {
    assert(values.contains(key), s"Key ${key.toUpperCase()} not found.")
    values.get(key).get.asInstanceOf[T]
  }

  def hash(str: String, salt: Option[Array[Byte]] = None) = {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.reset()
    if (salt.isDefined) digest.update(salt.get)
    new String(digest.digest(str.getBytes("UTF-8")))
  }

  def set(key: String, value: Any) = values.put(key, value)
}
