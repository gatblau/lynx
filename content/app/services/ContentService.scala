package services

import javax.inject.{Inject, Singleton}

import repositories.ContentRepository

@Singleton
class ContentService @Inject() (contentRepo: ContentRepository)
  extends Service {

//  def checkRequired(list : List[ActivationRequest]) : String = {
//    var ids = ListBuffer[Int]()
//    for (i <- 0 to list.length - 1) {
//      if (isUndefined(list(i).activationCode)) return "Activation Code is required."
//      if (list(i).countryId == 0) return "Country is required."
//      if (list(i).groupId == 0) return "Group is required."
//      if (list(i).preferredLanguageId == 0) return "Preferred Language is required."
//    }
//    ""
//  }
}
