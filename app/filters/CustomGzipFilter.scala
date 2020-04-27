package filters

import akka.stream.Materializer
import javax.inject.Inject
import play.api.Configuration
import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter

class CustomGzipFilter @Inject()(con: Configuration, gzipFilter: GzipFilter)(
    implicit mat: Materializer)
    extends HttpFilters {

  def filters = {
    val compressionSize = con.getInt("minCompressionSize")
    val newGzipFilter = new GzipFilter(shouldGzip = (request, response) =>
      response.body.contentLength.getOrElse[Long](0) > compressionSize.getOrElse(1400))
    Seq(newGzipFilter)
  }
}
