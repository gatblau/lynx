package lynx.api

import scala.beans.BeanProperty

case class Registration (
  @BeanProperty firstname: String,
  @BeanProperty lastname: String,
  @BeanProperty email: String,
  @BeanProperty emailTemplateId: Int
)