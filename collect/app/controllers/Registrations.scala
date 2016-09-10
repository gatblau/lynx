package controllers

import javax.inject.Inject

import lynx.api._
import play.api.libs.json.Reads._
import play.api.libs.json.{Writes, _}
import play.api.mvc.{Action, Controller}
import repositories.{GroupRepository, RegistrationRepository}

class Registrations extends Controller with ResultConverters {
  @Inject
  var repo: RegistrationRepository = _

  def create = Action(parse.json) { request =>
    val registrationList = null//request.body.as[List[Registration]]
    val result = repo.register(registrationList)
    Ok(Json.toJson(result))
  }

}
