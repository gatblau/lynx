package util

import scala.collection.mutable

object Cache {
  private var values = getCache

  private[Cache] def getCache : mutable.WeakHashMap[String, Any] = {
    if (values == null) {
      values = new mutable.WeakHashMap[String, Any]()
    }
    values
  }

  def get[T](key: String): T = {
    values.get(key).get.asInstanceOf[T]
  }

  def set(key: String, value: Any) {
    values.put(key, value)
  }
}
