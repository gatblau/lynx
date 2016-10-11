package lynx.api

import scala.beans.BeanProperty

case class SectionData(
   @BeanProperty name: String,
   @BeanProperty description: String,
   @BeanProperty dynamic: Boolean,
   @BeanProperty items: Array[ItemData]
)
