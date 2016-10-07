package controllers

import javax.ws.rs.core.MediaType

import akka.util.ByteString
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import lynx.api.ApiResult
import play.api.http.Writeable
import play.api.libs.json.Json
import play.api.mvc.{Codec, Controller, Result}

trait ControllerUtility extends Controller {

  implicit val apiResultFormat = Json.format[ApiResult]

  implicit def writeableOf_Array(implicit codec: Codec): Writeable[Array[ApiResult]] =
    Writeable(array => ByteString(mapper.writeValueAsString(array)), Some(MediaType.APPLICATION_JSON))

  def Ok(result : ApiResult) : Result = Ok(Json.toJson(result))

  def mapper : ObjectMapper = {
    val m = new ObjectMapper()
    m.registerModule(DefaultScalaModule)
  }
}
