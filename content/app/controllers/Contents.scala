package controllers

import javax.inject.Inject

import lynx.api.{Content, Descriptor}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ContentService

class Contents @Inject() (contentService: ContentService)
  extends Controller with PayloadManagement {

  def create = Action(parse.json) { request =>
    // mind the order of the implicits!
    implicit val descriptorFormat = Json.format[Descriptor]
    implicit val contentFormat = Json.format[Content]
    var payload : Option[Array[Content]] = None
    try payload = Some(request.body.as[Array[Content]]) catch { case _ : Throwable => }
    process(payload, contentService.validate, contentService.create)
  }
}
