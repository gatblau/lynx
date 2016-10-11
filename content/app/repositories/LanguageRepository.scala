package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.Language

@Singleton
class LanguageRepository extends Repository {

  def findByCode(code: String) : Language = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Language] {
      override def apply(em: EntityManager): Language = {
        val q = jpa.em().createNamedQuery(Language.FIND_BY_CODE, classOf[Language])
        q.setParameter("code", code)
        q.getSingleResult()
      }
    })
  }

  def find(id: Int) : Language = find(classOf[Language], id)
}
