package repositories

import javax.inject.Singleton
import javax.persistence.{EntityManager, PersistenceContext}

import lynx.api.Result

@Singleton
class GroupRepository {

  @PersistenceContext( unitName = "" )
  var em : EntityManager = _

  def create(group: lynx.api.Group) : Result[lynx.api.Group] = {
    try {
      val entity = em.merge(map(group))
      new Result[lynx.api.Group](
        success = true,
        id = entity.getId(),
        None,
        ""
      )
    }
    catch {
      case e : Exception => new Result[lynx.api.Group](
        success = false,
        id = -1,
        None,
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
