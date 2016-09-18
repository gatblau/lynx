package features.onboarding

import java.util.ArrayList
import javax.inject.Singleton

import cucumber.api.java.en.And
import lynx.api.Activation
import util.Keys._
import util.Testing

@Singleton
class ActivateSteps extends Testing {
  @And("^the activation details are known$")
  def the_activation_details_are_known() : Unit = {
    db.setup("/data/activation_details.xml")
    set(RESPONDENTS_INFO, db.load("/data/activation_details.xml").getTable("respondent"))
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
    val error : String = get(ERROR)
    assert(error == null || error.trim().length() == 0, s"Account could not be activated: $error")
  }

  @And("^the account details are updated$")
  def the_account_details_are_updated() : Unit = {
    val r = db.query("SELECT * FROM respondent;")
    val info : ArrayList[Activation] = get(ACTIVATION_DATA)
    for (i <- 0 to r.getRowCount() - 1) {
      val h1 = r.getValue(i, "pwd_hash").toString()
      val h2 = hash(info.get(i).password)
      assert(r.getValue(i, "activation_code") == null, "Activation Code was not removed.")
      assert(r.getValue(i, "firstname") == info.get(i).firstname, "Firstname was not updated.")
      assert(r.getValue(i, "lastname") == info.get(i).lastname, "Lastname was not updated.")
      assert(r.getValue(i, "telephone") == info.get(i).telephone, "Telephone number was not updated.")
      assert(r.getValue(i, "pwd_hash").toString().equals(hash(info.get(i).password)), "Password was not created.")
      assert(r.getValue(i, "role_id") == info.get(i).roleId, "Role was not set.")
      assert(r.getValue(i, "group_id") == info.get(i).groupId, "Group was not set.")
      assert(r.getValue(i, "preferred_language_id") == info.get(i).preferredLanguageId, "Preferred language was not set.")
      assert(r.getValue(i, "country_id") == info.get(i).countryId, "Country was not set.")
    }
  }

  private def activationPayloadOK()  = {
    val a = db.load("/data/respondents_to_be_activated.xml").getTable("respondent")
    val acts = new ArrayList[Activation]()
    for (i <- 0 to a.getRowCount() - 1) {
      acts.add(Activation(
        a.getValue(i, "firstname").toString(),
        a.getValue(i, "lastname").toString(),
        a.getValue(i, "email").toString(),
        a.getValue(i, "telephone").toString(),
        a.getValue(i, "password").toString(),
        a.getValue(i, "roled_id").toString().toInt,
        a.getValue(i, "group_id").toString().toInt,
        a.getValue(i, "preferred_language_id").toString().toInt,
        a.getValue(i, "country_id").toString().toInt,
        a.getValue(i, "activation_code").toString()))
    }
    acts
  }
}
