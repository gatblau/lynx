package repositories

import javax.inject.{Inject, Named, Singleton}
import javax.persistence.{EntityManager, PersistenceContext}

import lynx.api.ApiResult
import play.db.jpa.{JPAApi, Transactional}

@Singleton
class GroupRepository extends Repository {

  def create(group: lynx.api.Group) : ApiResult = {
    try {
      jpa.withTransaction(new java.util.function.Function[EntityManager, ApiResult] {
        override def apply(em: EntityManager): ApiResult = {
          val entity = em.merge(map(group))
          new ApiResult(
            success = true,
            id = entity.getId().toString(),
            "",
            ""
          )
        }
      })
    }
    catch {
      case e : Exception => {
        val result = new ApiResult(
          success = false,
          id = "",
          "",
          e.getMessage()
        )
        result
      }
    }
  }

  private def map(apiGroup : lynx.api.Group) : model.Group = {
    val g = new model.Group()
    g.setName(apiGroup.name)
    g
  }
}
