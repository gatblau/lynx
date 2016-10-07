package features.onboarding

import java.util.ArrayList
import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.ActivationRequest
import util.Keys._
import util.Testing

@Singleton
class ActivateSteps extends Testing {
  @And("^the activation details are known$")
  def the_activation_details_are_known() : Unit = {
    db.setup("/data/activation_details.xml")
    set(RESPONDENTS_INFO, db.load("/data/activation_details.xml").getTable("user"))
  }

  @And("^the all required account details are specified$")
  def the_all_required_account_details_are_specified() : Unit = {
    set(ACTIVATION_DATA, activationPayloadOK())
  }

  @And("^a request to activate the account is made$")
  def a_request_to_activate_the_account_is_made() : Unit = {
    try {
      set(ERROR, null)
      set(RESULT, client.activateAccount(get(ACTIVATION_DATA)))
    }
    catch {
      case ex : Exception => set(ERROR, ex.getMessage())
    }
  }

  @And("^the account is activated$")
  def the_account_is_activated() : Unit = {
    checkForError("Account could not be activated")
  }

  @And("^the account details are updated$")
  def the_account_details_are_updated() : Unit = {
    val r = db.query("SELECT * FROM user;")
    val info : Array[ActivationRequest] = get(ACTIVATION_DATA)
    for (i <- 0 to r.getRowCount() - 1) {
      val h1 = r.getValue(i, "pwd_hash").toString()
      val h2 = hash(info(i).password)
      assert(r.getValue(i, "activation_code") == null, "Activation Code was not removed.")
      assert(r.getValue(i, "firstname") == info(i).firstname, "Firstname was not updated.")
      assert(r.getValue(i, "lastname") == info(i).lastname, "Lastname was not updated.")
      assert(r.getValue(i, "telephone") == info(i).telephone, "Telephone number was not updated.")
      assert(r.getValue(i, "pwd_hash").toString().equals(hash(info(i).password)), "Password was not created.")
      assert(r.getValue(i, "role_id") == info(i).roleId, "Role was not set.")
      assert(r.getValue(i, "group_id") == info(i).groupId, "Group was not set.")
      assert(r.getValue(i, "preferred_language_id") == info(i).preferredLanguageId, "Preferred language was not set.")
      assert(r.getValue(i, "country_id") == info(i).countryId, "Country was not set.")
    }
  }

  private def activationPayloadOK()  = {
    val a = db.load("/data/users_to_be_activated.xml").getTable("user")
    val acts = new Array[ActivationRequest](a.getRowCount)
    for (i <- 0 to a.getRowCount() - 1) {
      acts(i) = ActivationRequest(
        a.getValue(i, "firstname").toString(),
        a.getValue(i, "lastname").toString(),
        a.getValue(i, "email").toString(),
        a.getValue(i, "telephone").toString(),
        a.getValue(i, "password").toString(),
        a.getValue(i, "roled_id").toString().toInt,
        a.getValue(i, "group_id").toString().toInt,
        a.getValue(i, "preferred_language_id").toString().toInt,
        a.getValue(i, "country_id").toString().toInt,
        a.getValue(i, "activation_code").toString())
    }
    acts
  }
}
