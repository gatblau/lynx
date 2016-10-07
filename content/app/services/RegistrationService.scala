package services

import javax.inject.{Inject, Singleton}
import javax.persistence.PersistenceException

import lynx.api.{ApiResult, PwdChangeRequest, RegistrationRequest}
import play.api.libs.mailer.{Email, MailerClient}
import repositories.{EmailTemplateRepository, UserRepository}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

@Singleton
class RegistrationService @Inject() (mailerClient: MailerClient, emailTemplateRepository: EmailTemplateRepository, userRepository: UserRepository)
  extends Service {

  def register(registrationList: Array[RegistrationRequest]): Array[ApiResult]= {
    var ids = ListBuffer[Int]()
    val results = Array[ApiResult]()
    for(i <- 0 to registrationList.length - 1) {
      breakable {
        val registration = registrationList(i)
        val id = registration.emailTemplateId
        if (!ids.contains(id)) {
          val found = emailTemplateRepository.findTemplate(id)
          if (found != null) {
            ids += id
          } else {
            results :+ ApiResult(id= registration.email, message = s"Email Template Id=$id is undefined.")
            break()
          }
        }
        try {
          val user = userRepository.register(registration, uuid())
          mail(registration.emailTemplateId,
                registration.firstname,
                registration.lastname,
                user.getEmail(),
                user.getActivationCode())
          results :+ ApiResult(success = true, id= registration.email, message = "Users have been created.")
        }
        catch {
          case pex : PersistenceException =>
            if (pex.getMessage.contains("ConstraintViolation"))
              results :+ ApiResult(id= registration.email, message = "User is already registered.")
            else throw pex
          case ex : Exception =>
            results :+ ApiResult(id= registration.email, message = "An error ocurred: " + ex.getMessage())
        }
      }
    }
    results
  }

  def changePassword(changeRequest: PwdChangeRequest) : ApiResult = {
    val user = userRepository.findByEmail(changeRequest.email)
    val template = emailTemplateRepository.findTemplate(changeRequest.emailTemplateId)
    if (user == null) return ApiResult.error(id= changeRequest.email, message = "User is not registered.")
    if (template == null) return ApiResult.error(id= changeRequest.email, message = s"Email template with id ${changeRequest.emailTemplateId} is not defined.")
    val activationCode = uuid()
    user.setActivationCode(activationCode)
    user.setPwdHash(null)
    userRepository.update(user)
    mail(changeRequest.emailTemplateId,
      user.getFirstname(),
      user.getLastname(),
      user.getEmail(),
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

  def checkRequired(requests : Array[RegistrationRequest]) : String = {
    for (i <- 0 to requests.length - 1) {
      if (isUndefined(requests(i).firstname)) return "Firstname is required."
      if (isUndefined(requests(i).lastname)) return "Lastname is required."
      if (isUndefined(requests(i).email)) return "Email is required."
      if (requests(i).emailTemplateId == 0) return "Email Template Id is required."
    }
    ""
  }

  def checkRequired(pwdChangeRequest: PwdChangeRequest) : String = {
    if (isUndefined(pwdChangeRequest.email)) return "Email is required."
    if (pwdChangeRequest.emailTemplateId == 0) return "An email template is required."
    ""
  }
}
