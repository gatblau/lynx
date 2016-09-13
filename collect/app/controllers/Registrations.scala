package controllers

import javax.inject.Inject
import javax.ws.rs.core.MediaType

import akka.util.ByteString
import lynx.api._
import play.api.Logger
import play.api.http.Writeable
import play.api.libs.json._
import play.api.mvc.{Action, Codec, Controller}
import services.RegistrationService

class Registrations extends Controller with ControllerUtility {

  implicit val registrationFormat = Json.format[Registration]
  implicit val apiResultFormat = Json.format[ApiResult]
  implicit def writeableOf_List(implicit codec: Codec): Writeable[List[ApiResult]] = {
    Writeable(list => ByteString(mapper.writeValueAsString(list)), Some(MediaType.APPLICATION_JSON))
  }

  @Inject
  var regService: RegistrationService = _

  def create = Action(parse.json) { request =>
    var result : ApiResult = null
    try {
      val registrationList = request.body.as[List[Registration]]
       Ok(regService.register(registrationList))
    }
    catch {
      case jex : JsResultException => {
        Logger.info(s"Malformed request: $jex.getMessage")
        BadRequest(Errors.INVALID_PAYLOAD)
      }
    }
  }
}
