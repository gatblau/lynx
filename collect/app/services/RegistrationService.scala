package services

import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.{Persistence, PersistenceException}
import javax.validation.{Constraint, ConstraintViolationException}

import lynx.api.{ApiResult, Registration}
import model.Respondent
import play.api.libs.mailer.{Email, MailerClient}
import repositories.{EmailTemplateRepository, RegistrationRepository}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

import scala.util.control.Breaks._

@Singleton
class RegistrationService @Inject() (mailerClient: MailerClient, emailTemplateRepository: EmailTemplateRepository, registrationRepository: RegistrationRepository) {

  def register(registrationList: List[Registration]): List[ApiResult]= {
    var ids = ListBuffer[Int]()
    val results = new mutable.MutableList[ApiResult]()
    for(i <- 0 to registrationList.length - 1) {
      breakable {
        val registration = registrationList(i)
        val id = registration.emailTemplateId
        if (!ids.contains(id)) {
          val found = emailTemplateRepository.findTemplate(id)
          if (found != null) {
            ids += id
          } else {
            results ++= List(
              new ApiResult(
                success = false,
                id= registration.email,
                message = s"Email Template Id=$id is undefined."))
            break()
          }
        }
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
          case pex : PersistenceException => {
            if (pex.getMessage.contains("ConstraintViolation")) {
              results ++= List(
                new ApiResult(success = false,
                  id= registration.email,
                  message = "Respondent is already registered."))
            }
            else throw pex
          }
          case ex : Exception => {
            val e = ex
            results ++= List(
              new ApiResult(success = false,
                id= registration.email,
                message = "An error ocurred: " + ex.getMessage()))
          }
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
