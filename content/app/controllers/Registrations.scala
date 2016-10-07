package controllers

import javax.inject.Inject

import lynx.api._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.RegistrationService

class Registrations @Inject() (regService: RegistrationService)
  extends Controller with ControllerUtility {

  implicit val registrationFormat = Json.format[RegistrationRequest]

  def create = Action(parse.json) { request =>
    var payload : Option[Array[RegistrationRequest]] = None
    try {
      payload = Some(request.body.as[Array[RegistrationRequest]])
    }
    catch {
      case _ =>
    }
    if (payload.isDefined) {
      val regs = payload.get
      val fieldsRequired = regService.checkRequired(regs)
      if (fieldsRequired.length() > 0) {
        BadRequest(Errors.INVALID_PAYLOAD + fieldsRequired)
      } else {
        Ok(regService.register(regs))
      }
    }
    else BadRequest(Errors.INVALID_PAYLOAD)
  }
}
