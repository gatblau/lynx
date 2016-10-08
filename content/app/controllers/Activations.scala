package controllers

import javax.inject.Inject

import lynx.api.ActivationRequest
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ActivationService

class Activations @Inject() (actService: ActivationService)
  extends Controller with PayloadManagement {

    implicit val activationFormat = Json.format[ActivationRequest]

    def create = Action(parse.json) { request =>
      var payload : Option[Array[ActivationRequest]] = None
      try payload = Some(request.body.as[Array[ActivationRequest]]) catch { case _ : Throwable => }
      process(payload, actService.checkRequired, actService.activate)
    }
}
