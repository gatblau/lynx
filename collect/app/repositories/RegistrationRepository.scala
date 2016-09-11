package repositories

import javax.inject.Singleton

import lynx.api.{ApiResult, Registration}
import model.Respondent

@Singleton
class RegistrationRepository extends Repository {
  def register(registrationList: List[Registration]): ApiResult = {
    for(i <- 0 to registrationList.length - 1) {
      merge(respondent(registrationList(i)))
    }
    new ApiResult(success = true, message = "Respondents have been created.")
  }

  def respondent(reg: Registration) = {
    val respondent = new Respondent()
    respondent.setActivationCode(hash(s"$reg.email|LocalDateTime.now().toString()"))
    respondent.setFirstname(reg.firstname)
    respondent.setLastname(reg.lastname)
    respondent.setEmail(reg.email)
    respondent.setEnabled(false)
    respondent
  }
}
