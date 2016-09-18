package lynx.api

import scala.beans.BeanProperty

case class Activation(
   @BeanProperty firstname: String,
   @BeanProperty lastname: String,
   @BeanProperty email: String,
   @BeanProperty telephone: String,
   @BeanProperty password: String,
   @BeanProperty roleId: Int,
   @BeanProperty groupId: Int,
   @BeanProperty preferredLanguageId: Int,
   @BeanProperty countryId: Int,
   @BeanProperty activationCode: String)
