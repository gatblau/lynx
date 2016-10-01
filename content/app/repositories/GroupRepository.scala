package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import lynx.api.ApiResult
import model.Group

@Singleton
class GroupRepository extends Repository {

  def create(group: lynx.api.Group) : ApiResult = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, ApiResult] {
      override def apply(em: EntityManager): ApiResult = {
        val entity = em.merge(map(group))
        ApiResult.ok(id = entity.getId().toString())
      }
    })
  }

  def find(id: Int) : Group = find(classOf[Group], id)

  private def map(apiGroup : lynx.api.Group) : model.Group = {
    val g = new model.Group()
    g.setName(apiGroup.name)
    g
  }
}
