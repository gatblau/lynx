package features.token

import java.time.{ZoneId, ZonedDateTime}

import cucumber.api.java.en.And
import lynx.Token
import util.{Cache, Keys}

class TokenSteps {
  private final val IP : String = "192.168.0.1"
  private final val LOGIN_TIME : ZonedDateTime = ZonedDateTime.of(2016, 9, 9, 0, 29, 0, 0, ZoneId.systemDefault())
  private final val USER_ID : Int = 10
  private final val USER_AGENT : String = "Mozilla 5.0"
  private final val KEY = "aswdcwe_cwc489846ghvjw0SFcjvPa9dv#fsv"
  private final val TOKEN = "1Y0+22AlJLEnnWfP3YKHJN0eT5+iMKn5zH5+jFaixBN7+7CPxI5jcY/XaV1ihk4I"

  @And("^the user id is known$")
  def the_user_id_is_known() : Unit = {
    Cache.set(Keys.USER_ID, USER_ID)
  }

  @And("^the login date and time is known$")
  def the_login_date_and_time_is_known() : Unit = {
    Cache.set(Keys.LOGIN_TIME, LOGIN_TIME)
  }

  @And("^the user IP is known$")
  def the_user_IP_is_known() : Unit = {
    Cache.set(Keys.IP, IP)
  }

  @And("^the user agent is known$")
  def the_user_agent_is_known() : Unit = {
    Cache.set(Keys.USER_AGENT, USER_AGENT)
  }

  @And("^the creation of an encrypted token is requested$")
  def the_creation_of_an_encrypted_token_is_requested() : Unit = {
    try {
      val token = new Token(Cache.get(Keys.USER_ID), Cache.get(Keys.LOGIN_TIME), Cache.get(Keys.IP), Cache.get(Keys.USER_AGENT))
        .toEncryptedString(KEY)
      System.out.println(s"token is = $token")
      Cache.set(TOKEN, token)
    }
    catch {
      case e : Exception => {
        val ex = e
        System.out.print(e.getMessage)
      }
    }
  }

  @And("^a valid token for a user is known$")
  def a_valid_token_for_a_user_is_known() : Unit = {
    Cache.set(Keys.TOKEN, TOKEN)
  }

  @And("^a request to read the token is made$")
  def a_request_to_read_the_token_is_made() : Unit = {
    try {
      Cache.set(Keys.TOKEN_VALUES, Token.fromEncryptedString(Cache.get(Keys.TOKEN), KEY))
    }
    catch {
      case ex : Exception => {
        System.out.print(ex.getMessage)
      }
    }
  }

  @And("^the token is valid")
  def the_token_is_valid() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
  }

  @And("^the user in the token matches the user id$")
  def the_user_in_the_token_matches_the_user_id() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
    assert(t.id == USER_ID)
  }

  @And("^the date in the token matches the login date$")
  def the_date_in_the_token_matches_the_login_date() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
    assert(t.loginTime == LOGIN_TIME)
  }

  @And("^the IP in the token matches the user IP$")
  def the_IP_in_the_token_matches_the_user_IP() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
    assert(t.ip == IP)
  }

  @And("^the user agent in the token matches the user agent$")
  def the_user_agent_in_the_token_matches_the_user_agent() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
    assert(t.userAgent == USER_AGENT)
  }

  @And("^a token that has been tampered with is known$")
  def a_token_that_has_been_tampered_with_is_known() : Unit = {
    Cache.set(Keys.TOKEN, TOKEN + "ed8d_")
  }

  @And("^the features.token is found to be invalid$")
  def the_token_is_found_to_be_invalid() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
  }

  @And("^an encrypted token is created$")
  def an_encrypted_token_is_created() : Unit = {
    val tokenString : String = Cache.get(TOKEN)
    val token = Token.fromEncryptedString(tokenString, KEY)
    assert(token != null)
  }
}
