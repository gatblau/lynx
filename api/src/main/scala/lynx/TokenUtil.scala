package lynx

import java.security.{Key, MessageDigest}
import java.time.format.{DateTimeFormatter, FormatStyle}
import java.time.{ZoneId, ZonedDateTime}
import java.util.{Base64, Locale}
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

class TokenUtil {
  private final val KEY_ALGORITHM : String = "AES"
  private final val DIGEST_ALGORITHM : String = "SHA-256"
  private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

  final val ivspec = new IvParameterSpec(Array[Byte]( 0, 3, 1, 9, 4, 7, 0, 8, 1, 2, 4, 3, 0, 6, 5, 5 ))

  final val formatter : DateTimeFormatter =
    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
      .withLocale(Locale.UK)
      .withZone(ZoneId.systemDefault());

  def create(id : Int, date : ZonedDateTime, ip: String, userAgent: String, key:String) : String = {
    encrypt(String.format("%s|%s|%s|%s", id.toString(), date.format(formatter), ip, userAgent), key)
  }

  def read(encToken: String, key: String) : Token = {
    var token : Token = null
    try {
      val value = decrypt(encToken, key)
      val values = value.split('|').map(_.trim)
      token = new Token(
        true,
        values(0).toInt,
        ZonedDateTime.parse(values(1), formatter),
        values(2),
        values(3))
    }
    catch {
      case e : Exception => {
        val ex = e
        token = new Token(false, -1, ZonedDateTime.now(), "", "")
      }
    }
    token
  }

  private def encrypt(value: String, key: String) : String = {
    cipher.init(Cipher.ENCRYPT_MODE, keyFromString(key), ivspec)
    val encryptedValue = cipher.doFinal(value.getBytes())
    new String(Base64.getEncoder().encodeToString(encryptedValue))
  }

  private def decrypt(encryptedValue: String, key: String) : String = {
    cipher.init(Cipher.DECRYPT_MODE, keyFromString(key), ivspec)
    val decodedValue = Base64.getDecoder().decode(encryptedValue)
    new String(cipher.doFinal(decodedValue))
  }

  private def keyFromString(keyString : String) : Key = {
    val digest = MessageDigest.getInstance(DIGEST_ALGORITHM)
    digest.update(keyString.getBytes())
    val key = new Array[Byte](16)
    System.arraycopy(digest.digest(), 0, key, 0, key.length)
    new SecretKeySpec(key, KEY_ALGORITHM)
  }
}