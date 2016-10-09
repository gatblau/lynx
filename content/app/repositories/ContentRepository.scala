package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.{Content, ContentDef, Section, SectionDef}

import scala.collection.JavaConversions._

@Singleton
class ContentRepository extends Repository {
  def create(c: lynx.api.Content) : Content = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Content] {
      override def apply(em: EntityManager): Content = {
        val contentDef = loadContentDef(c.contentDefId)
        val contentInstance = new model.Content()
        contentInstance.setContentDefId(contentDef)
        contentInstance.setCreated(now)
        persist(contentInstance)
        contentDef
          .getSectionDefContentDefViaContentDefId()
          .map(sectionDef => createSection(contentInstance, sectionDef, contentInstance.getId()))
        return contentInstance
      }
    })
  }

  private def loadContentDef(contentDefId: Int) : ContentDef = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, ContentDef] {
      override def apply(em: EntityManager): ContentDef = {
        val contentDef = em.find(classOf[ContentDef], int2Integer(contentDefId))
        contentDef.getSectionDefContentDefViaContentDefId().size()
        contentDef
      }
    })
  }

  def createSection(content: model.Content, sectionDef: SectionDef, contentId: Integer): Unit = {
    val section = new Section()
    section.setContentId(content)
    section.setSectionDefId(sectionDef)
    content.addSectionContentViaContentId(section)
    persist(section)
  }
}
