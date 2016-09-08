package features.token

import java.time.{LocalDate, ZonedDateTime}
import java.util.Date
import javax.inject.Inject

import cucumber.api.java.en.And
import lynx.{Token, TokenUtil}
import util.{Cache, Keys}

class TokenSteps {
  private final val IP : String = "192.168.0.1"
  private final val LOGIN_TIME : ZonedDateTime = ZonedDateTime.now()
  private final val USER_ID : Int = 10
  private final val USER_AGENT : String = "Mozilla 5.0"
  private final val KEY = "aswdcwe_cwc489846ghvjw0SFcjvPa9dv#fsv"
  private final val TOKEN = "1Y0+22AlJLEnnWfP3YKHJN0eT5+iMKn5zH5+jFaixBN7+7CPxI5jcY/XaV1ihk4I"

  @Inject
  var tokenUtil : TokenUtil = _

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
    Cache.set(Keys.SUCCESS, false)
    try {
      val token = tokenUtil.create(
        Cache.get(Keys.USER_ID),
        Cache.get(Keys.LOGIN_TIME),
        Cache.get(Keys.IP),
        Cache.get(Keys.USER_AGENT),
        KEY
      )
      Cache.set(Keys.SUCCESS, true)
      System.out.println(s"token is = $token")
    }
    catch {
      case e : Exception => {
        val ex = e
        System.out.print(e.getMessage)
      }
    }
  }

  @And("^the token is created$")
  def the_token_is_created() : Unit = {
    assert(Cache.get(Keys.SUCCESS))
  }

  @And("^an encrypted token for a user is known$")
  def an_encrypted_token_for_a_user_is_known() : Unit = {
    Cache.set(Keys.TOKEN, TOKEN)
  }

  @And("^a request to read the token is made$")
  def a_request_to_read_the_token_is_made() : Unit = {
    Cache.set(Keys.SUCCESS, false)
    try {
      val t =  tokenUtil.read(Cache.get(Keys.TOKEN), KEY)
      Cache.set(Keys.TOKEN_VALUES, t)
      Cache.set(Keys.SUCCESS, true)
    }
    catch {
      case e: Exception =>
    }
  }

  @And("^the token is read$")
  def the_token_is_read() : Unit = {
    assert(Cache.get(Keys.SUCCESS))
  }

  @And("^the user in the token matches the user id$")
  def the_user_in_the_token_matches_the_user_id() : Unit = {
    val t : Token = Cache.get(Keys.TOKEN_VALUES)
    assert(t.id == USER_ID)
  }

  @And("^the date in the token matches the login date$")
  def the_date_in_the_token_matches_the_login_date() : Unit = {

  }

  @And("^the IP in the token matches the user IP$")
  def the_IP_in_the_token_matches_the_user_IP() : Unit = {

  }

  @And("^the user agent in the token matches the user agent$")
  def the_user_agent_in_the_token_matches_the_user_agent() : Unit = {

  }
}
