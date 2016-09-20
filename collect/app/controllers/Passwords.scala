package controllers

import javax.inject.Inject

import lynx.api.{ApiResult, PwdChangeRequest}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.RegistrationService

class Passwords @Inject() (regService: RegistrationService)
  extends Controller with ControllerUtility {

  implicit val pwdChangeFormat = Json.format[PwdChangeRequest]
  implicit val apiResultFormat = Json.format[ApiResult]

  def change = Action(parse.json) { request =>
    val changeRequest = request.body.as[PwdChangeRequest]
    val fieldsRequired = regService.checkRequired(changeRequest)
    if (fieldsRequired.length() > 0) {
      BadRequest(Errors.INVALID_PAYLOAD + fieldsRequired)
    } else {
      Ok(Json.toJson(regService.changePassword(changeRequest)))
    }
  }
}

