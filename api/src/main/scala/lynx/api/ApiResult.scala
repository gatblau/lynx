package lynx.api

import org.codehaus.jackson.annotate.{JsonCreator, JsonProperty}

 case class ApiResult @JsonCreator()(
      @JsonProperty("success") success: Boolean = false,
      @JsonProperty("id") id: String = "",
      @JsonProperty("entity") entity: String = "",
      @JsonProperty("message") message: String = "")