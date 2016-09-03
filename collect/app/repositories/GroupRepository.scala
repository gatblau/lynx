package repositories

import javax.inject.{Inject, Named, Singleton}
import javax.persistence.{EntityManager, PersistenceContext}

import lynx.api.Result
import play.db.jpa.{JPAApi, Transactional}

@Singleton
class GroupRepository {
  @Inject
  var db : JPAApi = _

  def create(group: lynx.api.Group) : Result = {
    try {
      db.withTransaction(new java.util.function.Function[EntityManager, Result] {
        override def apply(em: EntityManager): Result = {
          val entity = em.merge(map(group))
          new Result(
            success = true,
            id = entity.getId(),
            "",
            ""
          )
        }
      })
    }
    catch {
      case e : Exception => {
        val result = new Result(
          success = false,
          id = -1,
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
