package dto

import play.api.libs.json.{Json,JsValue}

case class apiResponse(message: String, status: String,data :JsValue)

object apiResponse {
  implicit val reads = Json.reads[apiResponse]
  implicit val writes = Json.writes[apiResponse]
}