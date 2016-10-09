package repositories

import java.security.MessageDigest
import java.util.Calendar
import javax.inject.Inject
import javax.persistence.EntityManager

import org.hibernate.Hibernate
import play.db.jpa.JPAApi

class Repository {
  private final val DIGEST_ALGORITHM : String = "SHA-256"

  @Inject
  var jpa : JPAApi = _

  def merge[T](entity: T) : Unit = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Unit] {
      override def apply(em: EntityManager): Unit = {
        em.merge(entity)
      }
    })
  }

  def persist[T](entity: T) : Unit = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Unit] {
      override def apply(em: EntityManager): Unit = {
        em.persist(entity)
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

  def now : java.sql.Timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime())
}
