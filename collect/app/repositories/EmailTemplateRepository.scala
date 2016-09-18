package repositories

import javax.inject.Singleton
import model.EmailTemplate

@Singleton
class EmailTemplateRepository extends Repository {
  def findTemplate(templateId : Int) : EmailTemplate = find(classOf[EmailTemplate], templateId)
}
