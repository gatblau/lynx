package services

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}

import lynx.api.{ApiResult, Content}
import model.{ContentDef, Section, SectionDef}
import org.hibernate.Hibernate
import repositories.ContentRepository

import scala.collection.JavaConversions._

@Singleton
class ContentService @Inject() (contentRepo: ContentRepository)
  extends Service {

  def validate(items : Array[Content]) : String = {
    items.map(item => {
      if (item.contentDefId == 0) return "Content Definition Identifier is required."
      if (item.descriptions.length == 0)
        return "No descriptors for content instance found."
      else {
        item.descriptions.map(descriptor => {
            if (descriptor.name.length == 0) return "Descriptor requires a name."
            if (descriptor.language.length != 2) return "Invalid descriptor language code."
        })
      }
    })
    ""
  }

  def create(items : Array[Content]) : Array[ApiResult] = {
    items.map(c => createContent(c))
  }

  private def createContent(content: Content) : ApiResult = {
    val c = contentRepo.create(content)
    ApiResult.ok(id = c.getId().toString())
  }
}
