package repositories

import javax.inject.{Inject, Singleton}
import javax.persistence.EntityManager

import lynx.api.Descriptor
import model._

import scala.collection.JavaConversions._

@Singleton
class ContentRepository @Inject() (langRepo: LanguageRepository) extends Repository {
  def create(c: lynx.api.Content) : Option[Content] = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Option[Content]] {
      override def apply(em: EntityManager): Option[Content] = {
        val contentDef : Option[ContentDef] = loadContentDef(c.contentDefId)
        val contentInstance = new model.Content()
        if (contentDef.isDefined) {
          val cDef = contentDef.get
          contentInstance.setContentDefId(cDef)
          contentInstance.setCreated(now)
          persist(contentInstance)
          c.descriptions.map(desc => createContentLang(desc, contentInstance))
          cDef
            .getSectionDefContentDefViaContentDefId()
            .map(sectionDef => createSection(contentInstance, sectionDef))
          return Some(contentInstance)
        }
        return None
      }
    })
  }

  private def loadContentDef(contentDefId: Int) : Option[ContentDef]= {
    val contentDef = jpa.em().find(classOf[ContentDef], int2Integer(contentDefId))
    if (contentDef == null) return None
    contentDef.getSectionDefContentDefViaContentDefId().size()
    return Some(contentDef)
  }

  def createSection(content: model.Content, sectionDef: SectionDef): Unit = {
    val section = new Section()
    section.setContentId(content)
    section.setSectionDefId(sectionDef)
    content.addSectionContentViaContentId(section)
    persist(section)
    sectionDef
      .getItemDefSectionDefViaSectionDefId()
      .map(itemDef => createItem(section, itemDef))
  }

  def createItem(section: Section, itemDef: ItemDef): Unit = {
    val item = new Item()
    item.setItemDefId(itemDef)
    item.setSectionId(section)
    section.addItemSectionViaSectionId(item)
    persist(item)
    itemDef
      .getValueDefItemDefViaItemDefId()
      .map(valueDef => createValue(item, valueDef))
  }

  def createValue(item: Item, valueDef: ValueDef) : Unit = {
    val value = new Value()
    value.setValueDefId(valueDef)
    value.setItemId(item)
    item.addValueItemViaItemId(value)
    persist(value)
  }

  def createContentLang(desc: Descriptor, content: Content) = {
    val contentLang = new ContentLang()
    contentLang.setContentId(content)
    contentLang.setName(desc.name)
    contentLang.setDescription(desc.description)
    contentLang.setLanguageId(langRepo.findByCode(desc.language))
    persist(contentLang)
  }
}
