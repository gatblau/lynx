package controllers

import lynx.api.Result
import play.api.libs.json.{Json, Writes}

trait ResultConverters {
  implicit val resultWrites = new Writes[Result] {
    def writes(result: Result) = Json.obj(
      "id" -> result.id,
      "success" -> result.success,
      "entity" -> result.entity,
      "message" -> result.message
    )
  }
}
