package filters

import java.time.LocalDateTime

import akka.stream.Materializer
import javax.inject.Inject
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * A Logging Filter that is responsible for logging the
  * incoming API requests. This logs the HTTP request method, uri, query parameters,
  * response status, time of request, and execution in milliseconds
  */
class LoggingFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>
      val endTime     = System.currentTimeMillis
      val requestTime = endTime - startTime
      val ip = requestHeader.remoteAddress
      val time = LocalDateTime.now()
      Logger.info(
        s"${ip}  - [${time}] ${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}"
      )

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}