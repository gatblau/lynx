package controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import lynx.api.ApiResult
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{Controller, Result}

trait ControllerUtility extends Controller {
  implicit val resultWrites = new Writes[ApiResult] {
    def writes(result: ApiResult) = Json.obj(
      "id" -> result.id,
      "success" -> result.success,
      "entity" -> result.entity,
      "message" -> result.message
    )
  }

  def Ok(result : ApiResult) : Result = Ok(Json.toJson(result))
//  def Ok(results: java.util.List[ApiResult])

  def mapper : ObjectMapper = {
    val m = new ObjectMapper()
    m.registerModule(DefaultScalaModule)
  }
}
