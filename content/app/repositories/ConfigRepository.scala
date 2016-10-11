package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.{Configuration, Language}

@Singleton
class ConfigRepository extends Repository {

  private val KEY_DEFAULT_LANG = "DefaultLanguage"

  private def get[T](key: String) : Configuration = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Configuration] {
      override def apply(em: EntityManager): Configuration = {
        val q = jpa.em().createNamedQuery(Configuration.FIND_BY_KEY, classOf[Configuration])
        q.setParameter("key", key)
        q.getSingleResult()
      }
    })
  }

  def getDefaultLanguage : String = get(KEY_DEFAULT_LANG).getValue()
}
