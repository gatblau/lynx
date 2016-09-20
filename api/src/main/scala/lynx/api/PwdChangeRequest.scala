package lynx.api

import scala.beans.BeanProperty

case class PwdChangeRequest(
   @BeanProperty email: String,
   @BeanProperty emailTemplateId: Int)

