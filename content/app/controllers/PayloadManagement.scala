package controllers

import akka.util.ByteString
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import lynx.api.ApiResult
import play.api.http.Writeable
import play.api.libs.json.Json
import play.api.mvc.{Codec, Controller, Result}

trait PayloadManagement extends Controller {

  implicit val apiResultFormat = Json.format[ApiResult]

  implicit def writeableOf_Array(implicit codec: Codec): Writeable[Array[ApiResult]] =
    Writeable(array => ByteString(mapper.writeValueAsString(array)), Some("application/json"))

  def Ok(result : ApiResult) : Result = Ok(Json.toJson(result))

  def mapper : ObjectMapper = {
    val m = new ObjectMapper()
    m.registerModule(DefaultScalaModule)
  }

  def process[T](payload: Option[Array[T]], validate: (Array[T]) => String, execute: (Array[T]) => Array[ApiResult]) : Result = {
    if (payload.isDefined) {
      val body = payload.get
      val invalid = validate(body)
      if (invalid.length() > 0)
        BadRequest(Errors.INVALID_PAYLOAD + invalid)
      else
        Ok(execute(body))
    }
    else
      BadRequest(Errors.INVALID_PAYLOAD)
  }
}
