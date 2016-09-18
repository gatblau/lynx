package services

import javax.inject.{Inject, Singleton}

import lynx.api.{Activation, ApiResult}
import model.Respondent
import repositories._

import scala.collection.mutable.ListBuffer

@Singleton
class ActivationService @Inject() (
        respondentRepo: RespondentRepository,
        groupRepo: GroupRepository,
        roleRepo: RoleRepository,
        languageRepo: LanguageRepository,
        countryRepo: CountryRepository)
  extends Service {

  def activate(activationList: List[Activation]): List[ApiResult] = activationList.map(a => activate(a))

  private def activate(activation: Activation) : ApiResult = {
    val respondent : Respondent = respondentRepo.findByEmail(activation.email)
    if (respondent.getActivationCode().equals(activation.activationCode)) {
      val group = groupRepo.find(activation.groupId)
      val role = roleRepo.find(activation.roleId)
      val preferredLanguage = languageRepo.find(activation.preferredLanguageId)
      val country = countryRepo.find(activation.countryId)
      respondent.setActivationCode(null)
      respondent.setCountryId_(activation.countryId)
      respondent.setFirstname(activation.firstname)
      respondent.setLastname(activation.lastname)
      respondent.setTelephone(activation.telephone)
      respondent.setGroupId(group)
      respondent.setRoleId(role)
      respondent.setPreferredLanguageId(preferredLanguage)
      respondent.setCountryId(country)
      respondent.setEmail(activation.email)
      respondent.setPwdHash(hash(activation.password))
      respondent.setEnabled(true)
      respondentRepo.update(respondent)
      return new ApiResult(true, activation.email, "Activation successful.")
    } else {
      return new ApiResult(true, activation.email, "Activation failed: Invalid Activation Code.")
    }
  }

  def checkRequired(list : List[Activation]) : String = {
    var ids = ListBuffer[Int]()
    for (i <- 0 to list.length - 1) {
      if (isUndefined(list(i).activationCode)) return "Activation Code is required."
      if (list(i).countryId == 0) return "Country is required."
      if (list(i).groupId == 0) return "Group is required."
      if (list(i).preferredLanguageId == 0) return "Preferred Language is required."
    }
    ""
  }
}
