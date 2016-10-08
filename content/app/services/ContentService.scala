package services

import javax.inject.{Inject, Singleton}

import lynx.api.{ApiResult, Content}
import repositories.ContentRepository

@Singleton
class ContentService @Inject() (contentRepo: ContentRepository)
  extends Service {

  def checkRequired(items : Array[Content]) : String = {
    for (i <- 0 to items.length - 1) {
      if (items(i).contentDefId == 0) return "Content Definition Identifier is required."
    }
    ""
  }

  def create(items : Array[Content]) : Array[ApiResult] = {
    Array(ApiResult())
  }
}
