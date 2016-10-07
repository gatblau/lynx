package controllers

import lynx.api.{ApiResult, Content, Descriptor}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class Contents extends Controller with ControllerUtility {

  def create = Action(parse.json) { request =>
    // mind the order of the implicits
    implicit val descriptorFormat = Json.format[Descriptor]
    implicit val contentFormat = Json.format[Content]

    val registrationList = request.body.as[Array[Content]]
    Ok(Array(ApiResult()))
  }
}
