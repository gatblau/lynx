package repositories

import javax.inject.Singleton
import javax.persistence.EntityManager

import lynx.api.{Activation, Registration}
import model.Respondent

@Singleton
class RespondentRepository extends Repository {

  def register(reg: Registration, activationCode: String) = {
    val respondent = new Respondent()
    respondent.setActivationCode(activationCode)
    respondent.setFirstname(reg.firstname)
    respondent.setLastname(reg.lastname)
    respondent.setEmail(reg.email)
    respondent.setEnabled(false)
    merge(respondent)
    respondent
  }

  def findByEmail(email: String) : Respondent = {
    jpa.withTransaction(new java.util.function.Function[EntityManager, Respondent] {
      override def apply(em: EntityManager): Respondent = {
        val q = jpa.em().createNamedQuery(Respondent.FIND_BY_EMAIL, classOf[Respondent])
        q.setParameter("email", email)
        q.getSingleResult()
      }
    })
  }

  def update(respondent: Respondent) : Unit = merge(respondent)
}
