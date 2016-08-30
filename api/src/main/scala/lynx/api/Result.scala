package lynx.api

case class Result[T](
   success: Boolean,
   id: Int,
   entity: Option[T],
   message: String)
