package services

import javax.inject.{Inject, Singleton}

import lynx.api.{ApiResult, Content}
import repositories.ContentRepository

@Singleton
class ContentService @Inject() (contentRepo: ContentRepository)
  extends Service {

  def validate(items : Array[Content]) : String = {
    items.map(item => {
      if (item.contentDefId == 0) return "Content Definition Identifier is required."
      if (item.descriptions.length == 0)
        return "No descriptors for content instance found ."
      else {
        item.descriptions.map(descriptor => {
            if (descriptor.name.length == 0) return "Descriptor requires a name."
            if (descriptor.language.length == 0) return "Descriptor requires a language."
        })
      }
    })
    ""
  }

  def create(items : Array[Content]) : Array[ApiResult] = {
    Array(ApiResult())
  }
}
