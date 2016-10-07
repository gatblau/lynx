package services

import javax.inject.{Inject, Singleton}

import lynx.api.{ActivationRequest, ApiResult, PwdChangeRequest}
import model.User
import repositories._

import scala.collection.mutable.ListBuffer

@Singleton
class ActivationService @Inject() (
    userRepo: UserRepository,
    groupRepo: GroupRepository,
    roleRepo: RoleRepository,
    languageRepo: LanguageRepository,
    countryRepo: CountryRepository)
  extends Service {

  def activate(activations: Array[ActivationRequest]): Array[ApiResult] = activations.map(a => activate(a))

  private def activate(activation: ActivationRequest) : ApiResult = {
    val user : User = userRepo.findByEmail(activation.email)
    if (user.getActivationCode().equals(activation.activationCode)) {
      val group = groupRepo.find(activation.groupId)
      val role = roleRepo.find(activation.roleId)
      val preferredLanguage = languageRepo.find(activation.preferredLanguageId)
      val country = countryRepo.find(activation.countryId)
      user.setActivationCode(null)
      user.setCountryId_(activation.countryId)
      user.setFirstname(activation.firstname)
      user.setLastname(activation.lastname)
      user.setTelephone(activation.telephone)
      user.setGroupId(group)
      user.setRoleId(role)
      user.setPreferredLanguageId(preferredLanguage)
      user.setCountryId(country)
      user.setEmail(activation.email)
      user.setPwdHash(hash(activation.password))
      user.setEnabled(true)
      userRepo.update(user)
      return new ApiResult(true, activation.email, "Activation successful.")
    } else {
      return new ApiResult(true, activation.email, "Activation failed: Invalid Activation Code.")
    }
  }

  def checkRequired(requests : Array[ActivationRequest]) : String = {
    for (i <- 0 to requests.length - 1) {
      if (isUndefined(requests(i).activationCode)) return "Activation Code is required."
      if (requests(i).countryId == 0) return "Country is required."
      if (requests(i).groupId == 0) return "Group is required."
      if (requests(i).preferredLanguageId == 0) return "Preferred Language is required."
    }
    ""
  }
}