package controllers

import play.api.libs.json.{JsValue, Json}

case class Response(code: Int, body: JsValue)

object Response {
  implicit val reads = Json.reads[Response]
  implicit val writes = Json.writes[Response]
}
