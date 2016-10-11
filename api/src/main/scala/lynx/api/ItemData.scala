package lynx.api

import scala.beans.BeanProperty

case class ItemData(
  @BeanProperty name: String,
  @BeanProperty description: String,
  @BeanProperty question: String,
  @BeanProperty regex: String,
  @BeanProperty max: String,
  @BeanProperty min: String,
  @BeanProperty `type`: String,
  @BeanProperty layout: String,
  @BeanProperty values: Array[ValueData]
)