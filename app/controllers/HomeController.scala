package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

import scala.util.{Failure, Success, Try}

/**
 * This controller creates an `Action` to handle HTTP dto.requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Map[String,String]("status"->"200","message" -> "Covid 19 App For Docs")))
  }
}
