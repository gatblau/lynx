package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.Language

@Singleton
class LanguageRepository extends Repository {
  def find(id: Int) : Language = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Language] {
      override def apply(em: EntityManager): Language = {
        em.find(classOf[Language], id)
      }
    })
  }
}
