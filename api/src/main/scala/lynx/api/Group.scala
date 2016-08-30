package lynx.api

import scala.beans.BeanProperty

case class Group(
  @BeanProperty var name: String,
  @BeanProperty var description: String)
