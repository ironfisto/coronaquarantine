package filters

import javax.inject.Inject
import play.api.http.{DefaultHttpFilters, EnabledFilters}
import play.filters.gzip.GzipFilter

/**
  * Main Entry Point definition of Filters that we would want Play! Framework to execute
  * If we need to add more filters, we add it here as a Parameter but the filter implementation
  * is located on a different or same package This class is used and as the value for
  * play.http.filters inside the application.conf
  * (e.g play.http.filters=filters.Filters)
  */
class Filters @Inject()(defaultFilters: EnabledFilters,
                        gzip: GzipFilter,
                        log: LoggingFilter
                       ) extends DefaultHttpFilters(defaultFilters.filters :+ gzip :+ log: _*)