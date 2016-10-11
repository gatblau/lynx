package lynx.api

import scala.beans.BeanProperty

case class ContentData(
  @BeanProperty name: String,
  @BeanProperty description: String,
  @BeanProperty sections: Array[SectionData]
)
