package lynx

import java.time.ZonedDateTime

case class Token(
    val valid: Boolean,
    val id: Int,
    val loginTime: ZonedDateTime,
    val ip: String,
    val userAgent: String)
