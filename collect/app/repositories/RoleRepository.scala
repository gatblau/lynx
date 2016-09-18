package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import model.Role

@Singleton
class RoleRepository extends Repository {
  def find(id: Int) : Role = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Role] {
      override def apply(em: EntityManager): Role = {
        em.find(classOf[Role], id)
      }
    })
  }
}
