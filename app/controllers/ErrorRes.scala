package controllers

import play.api.libs.json.Json

case class ErrorRes(api: String, message: String)

object ErrorRes {
  implicit val reads = Json.reads[ErrorRes]
  implicit val writes = Json.writes[ErrorRes]
}
