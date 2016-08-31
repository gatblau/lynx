package lynx.api

import org.codehaus.jackson.annotate.{JsonCreator, JsonProperty}

 case class Result @JsonCreator()(
      @JsonProperty("success") success: Boolean,
      @JsonProperty("id") id: Int,
      @JsonProperty("entity") entity: String,
      @JsonProperty("message") message: String)