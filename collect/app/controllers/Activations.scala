package controllers

import javax.inject.Inject
import javax.ws.rs.core.MediaType

import akka.util.ByteString
import lynx.api.{ActivationRequest, ApiResult}
import play.api.http.Writeable
import play.api.libs.json.Json
import play.api.mvc.{Action, Codec, Controller}
import services.ActivationService

class Activations @Inject() (actService: ActivationService)
  extends Controller with ControllerUtility {

    implicit val activationFormat = Json.format[ActivationRequest]
    implicit val apiResultFormat = Json.format[ApiResult]
    implicit def writeableOf_List(implicit codec: Codec): Writeable[List[ApiResult]] = Writeable(list => ByteString(mapper.writeValueAsString(list)), Some(MediaType.APPLICATION_JSON))

    def create = Action(parse.json) { request =>
      val activationList = request.body.as[List[ActivationRequest]]
      val fieldsRequired = actService.checkRequired(activationList)
      if (activationList.length == 0) {
        BadRequest(Errors.INVALID_PAYLOAD)
      } else if (fieldsRequired.length() > 0) {
        BadRequest(Errors.INVALID_PAYLOAD + fieldsRequired)
      } else {
        Ok(actService.activate(activationList))
      }
    }
}
