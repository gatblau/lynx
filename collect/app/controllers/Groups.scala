package controllers

import javax.inject.Inject

import lynx.api._
import play.api.libs.json.Reads._
import play.api.libs.json.{Writes, _}
import play.api.mvc.{Action, Controller}
import repositories.GroupRepository

class Groups extends Controller with ResultConverters {

  @Inject
  var repo: GroupRepository = _

  def create = Action(parse.json) { request =>
    val group = new Group((request.body \ "name").as[String])
    val result = repo.create(group)
    Ok(Json.toJson(result))
  }
}

