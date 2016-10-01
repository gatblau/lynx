package repositories

import java.security.MessageDigest
import javax.inject.Inject
import javax.persistence.EntityManager

import play.db.jpa.JPAApi

class Repository {
  @Inject
  var jpa : JPAApi = _

  def merge[T](entity: T) : Unit = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Unit] {
      override def apply(em: EntityManager): Unit = {
        em.merge(entity)
      }
    })
  }

  def find[T](cls: Class[T], id: Int) : T = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, T] {
      override def apply(em: EntityManager): T = {
        em.find(cls, id)
      }
    })
  }

  def hash(s: String) = new String(MessageDigest.getInstance("SHA").digest(s.getBytes))
}
