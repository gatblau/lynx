package services

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}

import lynx.api.{ApiResult, ContentCreate, ContentData}
import model.{ContentDef, Section, SectionDef}
import org.hibernate.Hibernate
import repositories.{ConfigRepository, ContentRepository}

import scala.collection.JavaConversions._

@Singleton
class ContentService @Inject() (contentRepo: ContentRepository, configRepo: ConfigRepository)
  extends Service {

  def validate(items : Array[ContentCreate]) : String = {
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

  def create(items : Array[ContentCreate]) : Array[ApiResult] = {
    items.map(c => createContent(c))
  }

  def validateContentData() : String = {
    ""
  }

  def get(contentId: Int, lang: Option[String]) : ContentData = {
    val code = if (lang.isDefined) lang.get else configRepo.getDefaultLanguage
    ???
  }

  private def createContent(content: ContentCreate) : ApiResult = {
    val c = contentRepo.create(content)
    if (!c.isDefined) return ApiResult.error("Content definition does not exist.")
    ApiResult.ok(id = c.get.getId().toString())
  }
}
