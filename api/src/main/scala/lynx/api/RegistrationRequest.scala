package lynx.api

import scala.beans.BeanProperty

case class RegistrationRequest(
  @BeanProperty firstname: String,
  @BeanProperty lastname: String,
  @BeanProperty email: String,
  @BeanProperty emailTemplateId: Int
)