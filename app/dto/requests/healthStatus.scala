package dto.requests

import play.api.libs.json.Json

case class healthStatus(healthPing: String)

object healthStatus {

  implicit val reads = Json.reads[isUserExists]
  implicit val writes = Json.writes[isUserExists]


}
