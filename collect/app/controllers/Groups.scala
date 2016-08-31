package controllers

import javax.inject.Inject

import play.api.libs.json._
import lynx.api._
import play.api.libs.json.Writes
import play.api.mvc.{Action, Controller}
import repositories.GroupRepository

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

class Groups extends Controller {

  @Inject
  var repo : GroupRepository = _

  def create(group : Group) = Action {
    val result = repo.create(group)
    Ok(Json.toJson(result))
  }

  implicit val resultWrites = new Writes[Result] {
    def writes(result: Result) = Json.obj(
      "id" -> result.id,
      "success" -> result.success,
      "entity" -> "",
      "message" -> ""
    )
  }

  implicit val groupReads = new Reads[Group] {
    override def reads(json: JsValue): Group = new Group(
      (json \ "name").as[String]
    )
  }
}
