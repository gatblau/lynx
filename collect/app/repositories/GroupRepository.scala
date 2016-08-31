package repositories

import javax.inject.Singleton
import javax.persistence.{EntityManager, PersistenceContext}

import lynx.api.Result

@Singleton
class GroupRepository {

  @PersistenceContext( unitName = "" )
  var em : EntityManager = _

  def create(group: lynx.api.Group) : Result = {
    try {
      val entity = em.merge(map(group))
      new Result(
        success = true,
        id = entity.getId(),
        "",
        ""
      )
    }
    catch {
      case e : Exception => new Result(
        success = false,
        id = -1,
        "",
        e.getMessage()
      )
    }
  }

  private def map(apiGroup : lynx.api.Group) : model.Group = {
    return new model.Group() {
      setName(apiGroup.name)
    }
  }
}
