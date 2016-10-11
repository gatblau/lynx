package lynx.api

import scala.beans.BeanProperty

case class ValueData(
  @BeanProperty id: Int,
  @BeanProperty value: String
)