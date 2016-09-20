package lynx.api

import org.codehaus.jackson.annotate.{JsonCreator, JsonProperty}

case class ApiResult @JsonCreator() (
  @JsonProperty(value = "success") success: Boolean = false,
  @JsonProperty(value = "id") id: String = "",
  @JsonProperty(value = "message") message: String = "")

object ApiResult {
  def error(id: String, message: String) = new ApiResult(success = false, id = id, message = message)
  def error(id: String = "", ex: Exception) : ApiResult = new ApiResult(success = false, id = id, message = ex.getMessage())
  def error(ex: Exception) : ApiResult = error("", ex)
  def ok(id: String = "", message: String = "") : ApiResult = new ApiResult(success = true, id = id, message = message)
}