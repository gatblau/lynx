package lynx.api

import scala.beans.BeanProperty

case class Descriptor(
   @BeanProperty name: String,
   @BeanProperty description: String,
   @BeanProperty language: String)
