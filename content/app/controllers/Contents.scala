package controllers

import javax.inject.Inject

import lynx.api._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ContentService

class Contents @Inject() (contentService: ContentService)
  extends Controller with PayloadManagement {

  def create = Action(parse.json) { request =>
    // mind the order of the implicits!
    implicit val descriptorFormat = Json.format[Descriptor]
    implicit val contentFormat = Json.format[ContentCreate]
    var payload : Option[Array[ContentCreate]] = None
    try payload = Some(request.body.as[Array[ContentCreate]]) catch { case _ : Throwable => }
    process(payload, contentService.validate, contentService.create)
  }

  def get(id: Int, lang: Option[String]) = Action(parse.json) { request =>
    implicit val valueDataFormat = Json.format[ValueData]
    implicit val itemDataFormat = Json.format[ItemData]
    implicit val sectionDataFormat = Json.format[SectionData]
    implicit val contentDataFormat = Json.format[ContentData]
    val result = contentService.get(id, lang)
    Ok(ApiResult.ok())
  }
}
