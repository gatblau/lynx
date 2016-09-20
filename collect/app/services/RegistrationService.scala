package services

import javax.inject.{Inject, Singleton}
import javax.persistence.PersistenceException

import lynx.api.{ApiResult, PwdChangeRequest, RegistrationRequest}
import model.Respondent
import play.api.libs.mailer.{Email, MailerClient}
import repositories.{EmailTemplateRepository, RespondentRepository}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

@Singleton
class RegistrationService @Inject() (mailerClient: MailerClient, emailTemplateRepository: EmailTemplateRepository, respondentRepository: RespondentRepository)
  extends Service {

  def register(registrationList: List[RegistrationRequest]): List[ApiResult]= {
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
          val respondent = respondentRepository.register(registration, uuid())
          mail(registration.emailTemplateId,
                registration.firstname,
                registration.lastname,
                respondent.getEmail(),
                respondent.getActivationCode())
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

  def changePassword(changeRequest: PwdChangeRequest) : ApiResult = {
    val respondent = respondentRepository.findByEmail(changeRequest.email)
    val template = emailTemplateRepository.findTemplate(changeRequest.emailTemplateId)
    if (respondent == null) return ApiResult.error(id= changeRequest.email, message = "Respondent is not registered.")
    if (template == null) return ApiResult.error(id= changeRequest.email, message = s"Email template with id ${changeRequest.emailTemplateId} is not defined.")
    val activationCode = uuid()
    respondent.setActivationCode(activationCode)
    respondent.setPwdHash(null)
    respondentRepository.update(respondent)
    mail(changeRequest.emailTemplateId,
      respondent.getFirstname(),
      respondent.getLastname(),
      respondent.getEmail(),
      activationCode)
    ApiResult.ok(id= changeRequest.email, message = "Email has been reset.")
  }

  private def mail(emailTemplateId: Int, firstname: String, lastname: String, mailTo: String, activationCode: String) : Unit = {
    val template = emailTemplateRepository.findTemplate(emailTemplateId)
    val bodyfirstname = template.getBody.replaceAll("\\{\\{firstname\\}\\}", firstname)
    val bodylastname = bodyfirstname.replaceAll("\\{\\{lastname\\}\\}|\\{\\{surname\\}\\}", lastname)
    val bodyactivationcode = bodylastname.replaceAll("\\{\\{activationcode\\}\\}", activationCode)
    val email = Email(
      template.getSubject(),
      "LYNX Admin FROM <no-reply@email.com",
      Seq(mailTo),
      bodyHtml = Some(bodyactivationcode)
    )
    mailerClient.send(email)
  }

  def checkRequired(list : List[RegistrationRequest]) : String = {
    for (i <- 0 to list.length - 1) {
      if (isUndefined(list(i).firstname)) return "Firstname is required."
      if (isUndefined(list(i).lastname)) return "Lastname is required."
      if (isUndefined(list(i).email)) return "Email is required."
      if (list(i).emailTemplateId == 0) return "Email Template Id is required."
    }
    ""
  }

  def checkRequired(pwdChangeRequest: PwdChangeRequest) : String = {
    if (isUndefined(pwdChangeRequest.email)) return "Email is required."
    if (pwdChangeRequest.emailTemplateId == 0) return "An email template is required."
    ""
  }
}
