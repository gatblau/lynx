package repositories

import javax.inject.Singleton

import lynx.api.{Registration, Result}


@Singleton
class RegistrationRepository extends Repository {
  def register(registrationList: List[Registration]): Result = {
    new Result(false, 0, "","")
  }

}
