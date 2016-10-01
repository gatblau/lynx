package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import lynx.api.RegistrationRequest
import model.User

@Singleton
class UserRepository extends Repository {

  def register(reg: RegistrationRequest, activationCode: String) = {
    val user = new User()
    user.setActivationCode(activationCode)
    user.setFirstname(reg.firstname)
    user.setLastname(reg.lastname)
    user.setEmail(reg.email)
    user.setEnabled(false)
    merge(user)
    user
  }

  def findByEmail(email: String) : User = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, User] {
      override def apply(em: EntityManager): User = {
        val q = jpa.em().createNamedQuery(User.FIND_BY_EMAIL, classOf[User])
        q.setParameter("email", email)
        q.getSingleResult()
      }
    })
  }

  def update(user: User) : Unit = merge(user)
}
