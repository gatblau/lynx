package controllers

import javax.inject.Inject

import lynx.api._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.RegistrationService

class Registrations @Inject() (regService: RegistrationService)
  extends Controller with PayloadManagement {
  def create = Action(parse.json) { request =>
    implicit val registrationFormat = Json.format[RegistrationRequest]
    var payload : Option[Array[RegistrationRequest]] = None
    try payload = Some(request.body.as[Array[RegistrationRequest]]) catch { case _ : Throwable => }
    process(payload, regService.checkRequired, regService.register)
  }
}
