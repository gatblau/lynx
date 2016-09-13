package services

import javax.inject.Inject
import javax.inject.Singleton

import lynx.api.{ApiResult, Registration}
import model.Respondent
import play.api.libs.mailer.{Email, MailerClient}
import repositories.{EmailTemplateRepository, RegistrationRepository}

import scala.collection.mutable

@Singleton
class RegistrationService @Inject() (mailerClient: MailerClient, emailTemplateRepository: EmailTemplateRepository, registrationRepository: RegistrationRepository) {

  def register(registrationList: List[Registration]): List[ApiResult]= {
    val results = new mutable.MutableList[ApiResult]()
    for(i <- 0 to registrationList.length - 1) {
      val registration = registrationList(i)
      try {
        val respondent = registrationRepository.createRespondent(registration)
        mail(registration, respondent)
        results ++= List(
          new ApiResult(
            success = true,
            id= registration.email,
            message = "Respondents have been created."))
      }
      catch {
        case ex : Exception => {
          results ++= List(
            new ApiResult(success = false,
              id= registration.email,
              message = "An error ocurred: " + ex.getMessage()))
        }
      }
    }
    return results.toList
  }

  private def mail(registration: Registration, respondent: Respondent) : Unit = {
    val template = emailTemplateRepository.findTemplate(registration.emailTemplateId)
    val bodyfirstname = template.getBody.replaceAll("\\{\\{firstname\\}\\}", registration.firstname)
    val bodylastname = bodyfirstname.replaceAll("\\{\\{lastname\\}\\}|\\{\\{surname\\}\\}", registration.lastname)
    val bodyactivationcode = bodylastname.replaceAll("\\{\\{activationcode\\}\\}", respondent.getActivationCode())
    val email = Email(
      template.getSubject(),
      "LYNX Admin FROM <no-reply@email.com",
      Seq(registration.email),
      bodyHtml = Some(bodyactivationcode)
    )
    mailerClient.send(email)
  }
}
