package lynx.api

import org.codehaus.jackson.annotate.{JsonCreator, JsonProperty}

 case class ApiResult @JsonCreator()(
      @JsonProperty("success") success: Boolean = false,
      @JsonProperty("id") id: Int = 0,
      @JsonProperty("entity") entity: String = "",
      @JsonProperty("message") message: String = "")