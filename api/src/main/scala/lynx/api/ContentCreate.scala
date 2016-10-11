package lynx.api

import scala.beans.BeanProperty

case class ContentCreate(
  @BeanProperty contentDefId: Int = 0,
  @BeanProperty descriptions: Array[Descriptor]
)
