package filters

import javax.inject.Inject
import play.api.http.DefaultHttpFilters
import play.filters.headers.SecurityHeadersFilter

class Filters @Inject() (
    loggingFilter: LoggingFilter,
    securityHeadersFilter: SecurityHeadersFilter)
  extends DefaultHttpFilters(loggingFilter, securityHeadersFilter)
