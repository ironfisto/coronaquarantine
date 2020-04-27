package dto.requests

import play.api.libs.json.Json

case class updateHealthStatus(patientId:String,healthStatus:String)

object updateHealthStatus {

  implicit val reads = Json.reads[updateHealthStatus]
  implicit val writes = Json.writes[updateHealthStatus]

}
