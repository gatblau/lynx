package controllers

import javax.inject.Inject
import javax.ws.rs.core.MediaType

import akka.util.ByteString
import lynx.api._
import play.api.http.Writeable
import play.api.libs.json._
import play.api.mvc.{Action, Codec, Controller}
import services.RegistrationService

class Registrations @Inject() (regService: RegistrationService)
  extends Controller with ControllerUtility {

  implicit val registrationFormat = Json.format[RegistrationRequest]
  implicit val apiResultFormat = Json.format[ApiResult]
  implicit def writeableOf_List(implicit codec: Codec): Writeable[List[ApiResult]] = Writeable(list => ByteString(mapper.writeValueAsString(list)), Some(MediaType.APPLICATION_JSON))

  def create = Action(parse.json) { request =>
    val registrationList = request.body.as[List[RegistrationRequest]]
    val fieldsRequired = regService.checkRequired(registrationList)
    if (registrationList.length == 0) {
      BadRequest(Errors.INVALID_PAYLOAD)
    } else if (fieldsRequired.length() > 0) {
      BadRequest(Errors.INVALID_PAYLOAD + fieldsRequired)
    } else {
      Ok(regService.register(registrationList))
    }
  }
}
