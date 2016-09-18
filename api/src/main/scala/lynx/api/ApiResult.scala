package lynx.api

case class ApiResult(success: Boolean = false, id: String = "", message: String = "")

object ApiResult {
  def error(id: String = "", ex: Exception) : ApiResult = new ApiResult(success = false, id = id, message = ex.getMessage())
  def error(ex: Exception) : ApiResult = error("", ex)
  def ok(id: String = "", msg: String = "") : ApiResult = new ApiResult(success = true, id = id, message = msg)
}