package controllers

import javax.inject.Inject
import javax.ws.rs.core.MediaType

import akka.util.ByteString
import lynx.api._
import play.api.http.Writeable
import play.api.libs.json._
import play.api.mvc.{Action, Codec, Controller}
import services.RegistrationService

import scala.collection.mutable.ListBuffer

class Registrations @Inject() (regService: RegistrationService)
  extends Controller with ControllerUtility {

  implicit val registrationFormat = Json.format[Registration]
  implicit val apiResultFormat = Json.format[ApiResult]
  implicit def writeableOf_List(implicit codec: Codec): Writeable[List[ApiResult]] = Writeable(list => ByteString(mapper.writeValueAsString(list)), Some(MediaType.APPLICATION_JSON))

  def create = Action(parse.json) { request =>
    val registrationList = request.body.as[List[Registration]]
    val fieldsRequired = checkRequired(registrationList)
    if (registrationList.length == 0) {
      BadRequest(Errors.INVALID_PAYLOAD)
    } else if (fieldsRequired.length() > 0) {
      BadRequest(Errors.INVALID_PAYLOAD + fieldsRequired)
    } else {
      Ok(regService.register(registrationList))
    }
  }

  private def checkRequired(list : List[Registration]) : String = {
    var ids = ListBuffer[Int]()
    for (i <- 0 to list.length - 1) {
      if (isUndefined(list(i).firstname)) return "Firstname is required."
      if (isUndefined(list(i).lastname)) return "Lastname is required."
      if (isUndefined(list(i).email)) return "Email is required."
      if (list(i).emailTemplateId == 0) return "Email Template Id is required."
    }
    ""
  }
}
