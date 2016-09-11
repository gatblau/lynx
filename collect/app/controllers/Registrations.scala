package controllers

import javax.inject.Inject

import lynx.api._
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.{Action, Controller, Result}
import repositories.RegistrationRepository

class Registrations extends Controller with ControllerUtility {

  implicit val format = Json.format[Registration]

  @Inject
  var repo: RegistrationRepository = _

  def create = Action(parse.json) { request =>
    var result : ApiResult = null
    try {
      val registrationList = request.body.as[List[Registration]]
      Ok(repo.register(registrationList))
    }
    catch {
      case jex : JsResultException => {
        Logger.info(s"Malformed request: $jex.getMessage")
        BadRequest(Errors.INVALID_PAYLOAD)
      }
    }
  }
}
