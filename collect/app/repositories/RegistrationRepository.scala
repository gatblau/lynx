package repositories

import javax.inject.Singleton

import lynx.api.Registration
import model.Respondent

@Singleton
class RegistrationRepository extends Repository {

  def createRespondent(reg: Registration) = {
    val respondent = new Respondent()
    respondent.setActivationCode(hash(s"$reg.email|LocalDateTime.now().toString()"))
    respondent.setFirstname(reg.firstname)
    respondent.setLastname(reg.lastname)
    respondent.setEmail(reg.email)
    respondent.setEnabled(false)
    merge(respondent)
    respondent
  }
}
