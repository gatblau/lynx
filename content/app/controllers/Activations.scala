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
  extends Controller with PayloadManagement {

    implicit val activationFormat = Json.format[ActivationRequest]

    def create = Action(parse.json) { request =>
      val activationList = request.body.as[Array[ActivationRequest]]
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
