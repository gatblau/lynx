package services

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

trait Service {
  def isUndefined(x: String) =  { x == null || x.trim.length() == 0 }

  def uuid() = java.util.UUID.randomUUID.toString()

  def hash(str: String, salt: Option[Array[Byte]] = None) = {
    val digest = MessageDigest.getInstance("SHA-256")
    digest.reset()
    if (salt.isDefined) digest.update(salt.get)
    new String(digest.digest(str.getBytes("UTF-8")))
  }
}
