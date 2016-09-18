package services

import javax.inject.{Inject, Singleton}
import javax.persistence.PersistenceException

import lynx.api.{ApiResult, Registration}
import model.Respondent
import play.api.libs.mailer.{Email, MailerClient}
import repositories.{EmailTemplateRepository, RespondentRepository}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

@Singleton
class RegistrationService @Inject() (mailerClient: MailerClient, emailTemplateRepository: EmailTemplateRepository, registrationRepository: RespondentRepository)
  extends Service {

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
          val respondent = registrationRepository.register(registration, uuid())
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

  def checkRequired(list : List[Registration]) : String = {
    for (i <- 0 to list.length - 1) {
      if (isUndefined(list(i).firstname)) return "Firstname is required."
      if (isUndefined(list(i).lastname)) return "Lastname is required."
      if (isUndefined(list(i).email)) return "Email is required."
      if (list(i).emailTemplateId == 0) return "Email Template Id is required."
    }
    ""
  }
}
