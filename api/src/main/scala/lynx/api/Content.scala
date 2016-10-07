package lynx.api

import scala.collection.mutable.ArrayBuffer
import scala.beans.BeanProperty

case class Content (
  @BeanProperty contentDefId: Int = 0,
  @BeanProperty descriptions: Array[Descriptor]
)
