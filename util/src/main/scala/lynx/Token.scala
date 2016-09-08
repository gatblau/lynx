package lynx

import java.security.{Key, MessageDigest, SecureRandom}
import java.text.DateFormat
import java.time.{Instant, LocalDate, ZoneId, ZonedDateTime}
import java.time.format.{DateTimeFormatter, FormatStyle}
import java.util.{Base64, Date, Locale}
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

// http://stackoverflow.com/questions/6538485/java-using-aes-256-and-128-symmetric-key-encryption
// http://stackoverflow.com/questions/5520640/encrypting-and-decrypting-using-java
// http://stackoverflow.com/questions/28025742/encrypt-and-decrypt-a-string-with-aes-128
class TokenUtil {
  private final val KEY_ALGORITHM : String = "AES"
  private final val DIGEST_ALGORITHM : String = "SHA-256"
  private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

  final val TOKEN_OK = 1
  final val TOKEN_INVALID_FORMAT = -1
  final val TOKEN_INVALID_IP = -2
  final val TOKEN_INVALID_USER_AGENT = -3
  final val TOKEN_EXPIRED = -4
  final val TOKEN_INVALID = -5
  final val ivspec = new IvParameterSpec(Array[Byte]( 0, 3, 1, 9, 4, 7, 0, 8, 1, 2, 4, 3, 0, 6, 5, 5 ))

  final val formatter : DateTimeFormatter =
    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
      .withLocale(Locale.UK)
      .withZone(ZoneId.systemDefault());

  def create(id : Int, date : ZonedDateTime, ip: String, userAgent: String, key:String) : String = {
    encrypt(String.format("%s|%s|%s|%s", id.toString(), date.format(formatter), ip, userAgent), key)
  }

  /**
    * example of unencrypted features.token = "12|07/11/16 18:15|192.168.0.1|Mozilla/5.0"
    */
  def read(encToken: String, key: String) : Token = {
    var token : Token = null
    try {
      val value = decrypt(encToken, key)
      val values : Array[String] = value.split("|")
      token = new Token(TOKEN_OK, values(0).toInt, ZonedDateTime.parse(values(1), formatter), values(2), values(3))
    }
    catch {
      case e : Exception => {
        // http://stackoverflow.com/questions/6669181/why-does-my-aes-encryption-throws-an-invalidkeyexception
        val ex = e
        token = new Token(TOKEN_INVALID, -1, ZonedDateTime.now(), "", "")
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

case class Token(val result: Int, val id: Int, val date: ZonedDateTime, val ip: String, val userAgent: String)