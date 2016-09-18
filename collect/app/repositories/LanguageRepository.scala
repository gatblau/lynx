package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.Language

@Singleton
class LanguageRepository extends Repository {
  def find(id: Int) : Language = find(classOf[Language], id)
}
